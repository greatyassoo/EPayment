package com.phase2.epayment.Controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phase2.epayment.AccountsDB.Account;
import com.phase2.epayment.AccountsDB.AccountsFetcher;
import com.phase2.epayment.AccountsDB.Transaction;
import com.phase2.epayment.AccountsDB.Transaction.TYPE;
import com.phase2.epayment.Payment.CashPayment;
import com.phase2.epayment.Payment.CreditPayment;
import com.phase2.epayment.Payment.DiscountController;
import com.phase2.epayment.Payment.PaymentType;
import com.phase2.epayment.Payment.WalletPayment;
import com.phase2.epayment.ServicesDB.Service;
import com.phase2.epayment.ServicesDB.ServicesDB;

@RestController
@RequestMapping(value = "/transaction")
public class PaymentController {

    AccountsFetcher accountsFetcher;
    DiscountController discountController;
    ServicesDB servicesDB;

    PaymentController(AccountsFetcher accountsFetcher, DiscountController discountController, ServicesDB servicesDB){
        this.accountsFetcher = accountsFetcher;
        this.discountController = discountController;
        this.servicesDB = servicesDB;
    }

    // body structure
    //[ userName,password,service,serviceProvider,paymentMethod,phoneNumber,amount,{payment type data} ] 
    @PostMapping(value = "/pay")
    public boolean pay(@RequestBody Map<String,String> body) throws Exception{

        String userEmail = body.get("userEmail");
        String password = body.get("password");
        Account account = accountsFetcher.getAccount(userEmail,password);
        if(account==null)
            throw new IllegalAccessError("Account does not exist");
            
        String serviceName = body.get("serviceName");
        Service service;
        try {
            service = servicesDB.getServices(serviceName).get(0);
        } catch (Exception e) {throw new IllegalAccessError("Service not found.");};
        
        
        String serviceProviderName = body.get("serviceProvider");
        String method = body.get("paymentMethod");
        String phoneNumber = body.get("phoneNumber");
        double discount = discountController.getUserDiscount(userEmail, password, serviceName);

        body.put("amount", String.valueOf(Double.parseDouble(body.get("amount"))*discount));
        body.put("walletBalance", String.valueOf(account.getWalletBalance()));

        PaymentType paymentType;
        switch(method.toLowerCase()){
            case("wallet"):
                //{payment type data} = [wallet balance]
                paymentType = new WalletPayment();
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

        if(!service.getPaymentTypes().contains(paymentType))
            throw new IllegalAccessError("Payment method not allowed.");

        if(!paymentType.Pay(body))
            return false;
        
        if(method.toLowerCase()=="wallet")
            account.setWalletBalance(account.getWalletBalance()-(Double.parseDouble(body.get("amount"))));
        
        account.addTransaction(new Transaction(TYPE.PAYMENT, serviceName, serviceProviderName, method, phoneNumber, Double.parseDouble(body.get("amount")), discount));
        return true;
    }   
}
