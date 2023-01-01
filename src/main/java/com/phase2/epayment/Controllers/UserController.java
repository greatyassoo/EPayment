package com.phase2.epayment.Controllers;

import java.util.LinkedList;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phase2.epayment.AccountsDB.*;
import com.phase2.epayment.AccountsDB.Transaction.TYPE;
import com.phase2.epayment.Payment.CreditPayment;
import com.phase2.epayment.Payment.PaymentType;
import com.phase2.epayment.ServicesDB.*;

@RestController
@RequestMapping(value = "/user")
public class UserController extends ServicesController {

    UserController(ServicesFetcher servicesFetcher,AccountsFetcher accountsFetcher) {
        this.servicesFetcher = servicesFetcher;
        this.accountsFetcher = accountsFetcher;
    }
    
    /**done
    *
    * increases provided account wallet balance through credit card
    *
    * @param userEmail the email of the user
    * @param password the password of the user
    * @param CCN 16 digit credit card number
    * @param PIN 4 digit credit card PIN
    * @param amount amount to be added
    * @return true if funding process is succesful
    * @throws IlegallAccessError if provided info is incorrect
    *
    * @note the parameters mentioned above are held in the argument "body" which is a map that holds key value pairs
    * of n parameters. The key is the parameter name and the value corresponds to the paremeter value. 
    * ie: "serviceName": "Mobile Recharge Service" 
    */
    @PostMapping(value = "/fund")
    public boolean fundAccount(@RequestBody Map<String,String> body) {

        String userEmail = body.get("userEmail");
        String password = body.get("password");
        Double amount = Double.parseDouble(body.get("amount"));
        
        Account account = getAccount(userEmail, password);
        if(account==null)
            throw new IllegalAccessError("Account doesn't exist.");

        try {
            PaymentType credit = new CreditPayment();
            if(!credit.Pay(body))
                throw new IllegalAccessError("Wrong info provided.");

            account.setWalletBalance(account.getWalletBalance() + amount);
            account.addTransaction(new Transaction(Transaction.TYPE.TOP_UP, "Recharge",
                        "CreditCard","CreditCard", "", amount, 0));
            return true;
        } catch (Exception e) {
            throw new IllegalAccessError("Transaction failed.");
        }
    }
    
    /** done
    *
    * searches and returns service(s) provided
    *
    * @param serviceName service search query value
    * @return list of services in the system that best match the query provided.
    *          returns ALL services if the provided query was empty.
    * @throws IlegallAccessError if no match is found
    *
    */
    @GetMapping(value = "/service")
    public LinkedList<Service> getServices(@RequestParam("serviceName") String serviceName) {
        LinkedList<Service> temp = servicesFetcher.getServices(serviceName);
        if(temp.size()==0)
            throw new IllegalAccessError("No match found.");
        return temp;
    }

    /** done
    *
    * returns transactions of the account provided
    *
    * @param userEmail the email of the user
    * @param password the password of the user
    * @return list of transactions of the account provided
    * @throws IlegallAccessError if account not found
    *
    */
    @GetMapping(value = "/transactions")
    public LinkedList<Transaction> getTransactions(@RequestParam("userEmail") String userEmail,
            @RequestParam("password") String password) {
        try{
            Account account = getAccount(userEmail, password);
            return account.getAllTransactions();
        }catch(Exception e){};
        return null;
    }

    /** done
    *
    * returns refund requests of the account provided
    *
    * @param userEmail the email of the user
    * @param password the password of the user
    * @return list of refund requests of the account provided
    * @throws IlegallAccessError if account not found
    *
    */
    @GetMapping(value = "/refund-request")
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

    /** done
    *
    * creates and adds a new refund request to provided account
    *
    * @param userEmail the email of the user
    * @param password the password of the user
    * @param transactionID the id of the transaction to be refunded
    * @return true if refund request successful 
    * @throws IlegallAccessError if account not found
    * @throws IlegallAccessError if transaction provided has a previous pending request
    * @throws IlegallAccessError if transaction provided is a top up transaction
    * @throws indexOutOfBoundsException if transaction does not exist
    *
    * @note the parameters mentioned above are held in the argument "body" which is a map that holds key value pairs
    * of n parameters. The key is the parameter name and the value corresponds to the paremeter value. 
    * ie: "serviceName": "Mobile Recharge Service" 
    */
    @PostMapping(value = "/refund-request")
    public boolean addRefundRequest(@RequestBody Map<String,String> body) {
        String userEmail = body.get("userEmail");
        String password = body.get("password");
        int transactionID = Integer.parseInt(body.get("transactionID"));
        
        Account account = getAccount(userEmail, password);
        if(account==null){
            throw new IllegalAccessError("Account doesn't exist.");
        }


        try{
            if(account.getRefundRequests().contains(transactionID))
                throw new IllegalAccessError("Transaction already added to refund request.");
    
            if(account.getTransaction(transactionID).getType().equals(TYPE.TOP_UP))
                throw new IllegalAccessError("You cannot refund a top up transaction.");
        }catch(Exception e){throw new IllegalAccessError("Error with transaction search.");}
        
        account.addRefundRequest(transactionID); 
        return true;
    }
}