package com.phase2.epayment.Controllers;

import java.util.LinkedList;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phase2.epayment.AccountsDB.*;
import com.phase2.epayment.AccountsDB.Transaction.TYPE;
import com.phase2.epayment.Payment.*;
import com.phase2.epayment.ServicesDB.Service;
import com.phase2.epayment.ServicesDB.ServiceProvider;
import com.phase2.epayment.ServicesDB.ServicesFetcher;

@RestController
@RequestMapping(value = "/transaction")
public class PaymentController {

    AccountsFetcher accountsFetcher;
    DiscountController discountController;
    ServicesFetcher servicesFetcher;

    PaymentController(AccountsFetcher accountsFetcher, DiscountController discountController, ServicesFetcher servicesFetcher){
        this.accountsFetcher = accountsFetcher;
        this.discountController = discountController;
        this.servicesFetcher = servicesFetcher;
    }


    // body structure
    //[ userEmail,password,service,serviceProvider,paymentMethod,phoneNumber,amount,{payment type data} ] 

    /**
    *
    * @param userEmail the email of the user
    * @param password the password of the user
    * @param phoneNumber user phone number
    * @param serviceName the name of the service the provider is going to be added to.
	* @param serviceProviderName the name of the service provider to be added to the service.
    * @param paymentMethod the choice of payment method wether it be 'wallet', 'cash', or 'credit'.
    *                      if invalid payment method is inserted, the function defualts to credit payment type.
    *
    * @param optionalCreditPaymentArgs these are two extra parameter fields if the payment type chosen was 'credit'.
                                     @parameter CCN 16 digit credit card number
                                     @parameter PIN 4 digit credit card PIN

    *
    * @param amount value of amount to pay
    * @return transaction index if succesful.
    * @throws IllegalAccessError if account credentials or service are invalid
    *
    */
    @PostMapping(value = "/payment")
    public int pay(@RequestBody Map<String,String> body) throws Exception{
        
        String userEmail = body.get("userEmail");
        String password = body.get("password");
        Account account = accountsFetcher.getAccount(userEmail,password);
        if(account==null)
            throw new IllegalAccessError("Account does not exist");

        String serviceName = body.get("serviceName");
        String serviceProviderName = body.get("serviceProviderName");
        
        Service service;
        try {
            service = servicesFetcher.getServices(serviceName).get(0);

            LinkedList<ServiceProvider> serviceProviders = service.getServiceProviders();

            boolean found = false;
            for(int i=0 ; i<serviceProviders.size() ; i++){
                if(serviceProviders.get(i).getName().equals(serviceProviderName)){
                    found = true;
                    break;
                }
            }
            if(!found)
                throw new IllegalAccessError();
        } catch (Exception e) {throw new IllegalAccessError("Service/ServiceProvider not found.");}
        
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

        LinkedList<PaymentType> acceptedPaymentTypes = service.getAllPaymentTypes();
        boolean found=false;
        for(int i=0 ; i<acceptedPaymentTypes.size() ; i++){
            if(acceptedPaymentTypes.get(i).getClass().getSimpleName().equals(paymentType.getClass().getSimpleName())){
                found=true;
                break;
            }
        }

        if(!found)
            throw new IllegalAccessError("Payment method not allowed.");

        if(!paymentType.Pay(body))
            throw new IllegalAccessError("Error payment method.");
        
        if(method.toLowerCase().equals("wallet"))
            account.setWalletBalance(account.getWalletBalance()-(Double.parseDouble(body.get("amount"))));
        
        account.addTransaction(new Transaction(TYPE.PAYMENT, serviceName, serviceProviderName, method, phoneNumber, Double.parseDouble(body.get("amount")), discount));
        return account.getTransaction(account.getAllTransactions().size()-1).getTransactionID();
    }   
}
