package com.phase2.epayment.Controllers;

import java.util.LinkedList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phase2.epayment.AccountsDB.*;
import com.phase2.epayment.AccountsDB.Transaction.TYPE;
import com.phase2.epayment.ServicesDB.*;

@RestController
@RequestMapping(value = "/user")
public class UserController extends ServicesController {

    UserController(ServicesDB servicesDB,AccountsFetcher accountsFetcher) {
        this.servicesDB = servicesDB;
        this.accountsFetcher = accountsFetcher;
    }

    //[ userEmail,password,CCN,PIN,amount ]
    @PostMapping(value = "/fund")
    public boolean fundAccount(@RequestBody LinkedList<String> body) {
        String userEmail = body.removeFirst();
        String password = body.removeFirst();
        String CCN = body.removeFirst();
        String PIN = body.removeFirst();
        Double amount = Double.parseDouble(body.removeFirst());
        
        Account account = getAccount(userEmail, password);
        try {
            if (!CCN.matches("-?\\d+(\\.\\d+)?") || 
            CCN.length() != 16 || PIN.length() != 4 || 
            !PIN.matches("-?\\d+(\\.\\d+)?") ||
            amount<=0)
                throw new IllegalAccessError("Wrong info provided.");
            account.setWalletBalance(account.getWalletBalance() + amount);
            account.addTransaction(
                    new Transaction(Transaction.TYPE.TOP_UP, "Recharge", "CreditCard","CreditCard", "", amount, 0));
            return true;
        } catch (Exception e) {
            throw new IllegalAccessError("Transaction failed.");
        }
    }
    
    @GetMapping(value = "/service")
    public LinkedList<Service> getServices(@RequestParam("serviceName") String serviceName) {
        LinkedList<Service> temp = new LinkedList<>();
        for (int i = 0; i < servicesDB.size(); i++){
            if (servicesDB.get(i).getName().toLowerCase().contains(serviceName.toLowerCase()))
                temp.addLast(servicesDB.get(i));
        }
        if(temp.size()==0)
            throw new IllegalAccessError("No match found.");
        return temp;
    }

    @GetMapping(value = "/transactions")
    public LinkedList<Transaction> getTransactions(@RequestParam("userEmail") String userEmail,
            @RequestParam("password") String password) {
        Account account = getAccount(userEmail, password);
        return account.getTransactions();
    }

    @GetMapping(value = "/refund-requests")
    public LinkedList<Transaction> getRefundRequests(@RequestParam("userEmail") String userEmail,
            @RequestParam("password") String password) {
        Account account = getAccount(userEmail, password);
        LinkedList<Integer> refundRequestsIndx = account.getRefundRequests();
        LinkedList<Transaction> refundRequests = new LinkedList<>();
        for (int i = 0; i < refundRequestsIndx.size(); i++) {
            refundRequests.addLast(account.getTransaction(refundRequestsIndx.get(i)));
        }
        return refundRequests;
    }

    //[ userEmail,password,transactionID ]
    @PostMapping(value = "/new-refund")
    public boolean addRefundRequest(@RequestBody LinkedList<String> body) {
        String userEmail = body.removeFirst();
        String password = body.removeFirst();
        int transactionID = Integer.parseInt(body.removeFirst());
        
        Account account = getAccount(userEmail, password);

        if(account.getRefundRequests().contains(transactionID))
            throw new IllegalAccessError("Transaction already added to refund request.");
    
        if(account.getTransaction(transactionID).getType().equals(TYPE.TOP_UP)){
            throw new IllegalAccessError("You cannot refund a top up request.");
        }
        account.addRefundRequest(transactionID); 
        return true;
    }
}