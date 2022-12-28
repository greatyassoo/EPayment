package com.phase2.epayment.Controllers;

import java.util.LinkedList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phase2.epayment.AccountsDB.*;
import com.phase2.epayment.ServicesDB.*;

@RestController
@RequestMapping(value = "/user")
public class UserController extends ServicesController {

    UserController(ServicesDB servicesDB,AccountsFetcher accountsFetcher) {
        this.servicesDB = servicesDB;
        this.accountsFetcher = accountsFetcher;
    }

    @PostMapping(value = "/fund")
    public boolean fundAccount(@RequestParam("userName") String userName,@RequestParam("password") String password,
    @RequestParam("CCN") String CCN,@RequestParam("PIN") String PIN,@RequestParam("amount") double amount) {
        Account account = getAccount(userName, password);
        try {
            if (!CCN.matches("-?\\d+(\\.\\d+)?") || CCN.length() != 16 || PIN.length() != 4|| !PIN.matches("-?\\d+(\\.\\d+)?"))
                return false;
            account.setWalletBalance(account.getWalletBalance() + amount);
            account.addTransaction(
                    new Transaction(Transaction.TYPE.TOP_UP, "Recharge", "CreditCard","CreditCard", "", amount, 0));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping(value = "/service")
    public Service getService(@RequestParam("serviceName") String serviceName) {
        Service temp = null;
        for (int i = 0; i < servicesDB.size(); i++) {
            if (servicesDB.get(i).getName().equals(serviceName))
                temp = servicesDB.get(i);
        }
        
        if (temp == null)
            throw new IllegalStateException("Service does not exist");
        return temp;
    }

    @GetMapping(value = "/transactions")
    public LinkedList<Transaction> getTransactions(@RequestParam("userName") String userName,
            @RequestParam("password") String password) {
        Account account = getAccount(userName, password);
        return account.getTransactions();
    }

    @GetMapping(value = "/refund-requests")
    public LinkedList<Transaction> getRefundRequests(@RequestParam("userName") String userName,
            @RequestParam("password") String password) {
        Account account = getAccount(userName, password);
        LinkedList<Integer> refundRequestsIndx = account.getRefundRequests();
        LinkedList<Transaction> refundRequests = new LinkedList<>();
        for (int i = 0; i < refundRequestsIndx.size(); i++) {
            refundRequests.addLast(account.getTransaction(refundRequestsIndx.get(i)));
        }
        return refundRequests;
    }

    @PostMapping(value = "/new-refund")
    public boolean addRefundRequest(@RequestParam("userName") String userName,
            @RequestParam("password") String password, @RequestParam("transactionID") int transactionID) {
        Account account = getAccount(userName, password);

        if (! account.getRefundRequests().contains(transactionID)) {
            account.addRefundRequest(transactionID);
            return true;
        }
        
        return false;
    }
}