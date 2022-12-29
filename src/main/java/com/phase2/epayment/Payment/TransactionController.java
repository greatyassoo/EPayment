package com.phase2.epayment.Payment;

import java.util.LinkedList;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phase2.epayment.AccountsDB.Account;
import com.phase2.epayment.AccountsDB.AccountsFetcher;
import com.phase2.epayment.AccountsDB.Transaction;
import com.phase2.epayment.AccountsDB.Transaction.TYPE;


@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

    AccountsFetcher accountsFetcher;
    DiscountController discountController;

    TransactionController(AccountsFetcher accountsFetcher, DiscountController discountController){
        this.accountsFetcher = accountsFetcher;
        this.discountController = discountController;
    }

    public static final String[] paymentMethods = {"Credit","Cash","Wallet"} ;

    // body structure
    //[ userName,password,service,serviceProvider,paymentMethod,phoneNumber,amount,{payment type data} ] 
    @PostMapping(value = "/pay")
    public boolean pay(@RequestBody LinkedList<String> body) throws Exception{

        String userEmail = body.removeFirst();
        String password = body.removeFirst();
        Account account = accountsFetcher.getAccount(userEmail,password);
        if(account==null)
            throw new IllegalAccessError("Account does not exist");
            
        String service = body.removeFirst();
        String serviceProvider = body.removeFirst();
        String method = body.removeFirst();
        String phoneNumber = body.removeFirst();
        double amount = Double.parseDouble(body.getFirst());
        double discount = discountController.getUserDiscount(userEmail, password, service);
        
        body.addFirst(String.valueOf(Double.parseDouble(body.removeFirst())*discount));

        PaymentType paymentType;
        switch(method.toLowerCase()){
            case("wallet"):
                //{payment type data} = [wallet balance]
                paymentType = new WalletPayment();
                body.addLast(String.valueOf(account.getWalletBalance()));
                break;

            case("cash"):
                //{payment type data} = []
                paymentType = new CashPayment();
                break;

            default: 
                //{payment type data} = [ CCN ,PIN ]
                paymentType = new CreditPayment();
                break;
        }    

        if(!paymentType.Pay(body))
            return false;
        
        if(method.toLowerCase()=="wallet")
            account.setWalletBalance(account.getWalletBalance()-(amount*discount));
        
        account.addTransaction(new Transaction(TYPE.PAYMENT, service, serviceProvider, method, phoneNumber, amount, discount));
        return true;
    }   
}
