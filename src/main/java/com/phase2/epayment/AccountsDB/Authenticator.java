package com.phase2.epayment.AccountsDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class Authenticator implements AccountAuthentication{
    @Autowired
    private AccountsFetcher accountsFetcher;

    Authenticator(AccountsFetcher accountsFetcher){
        this.accountsFetcher=accountsFetcher;
    }

    // -1 for error, 0 for admin, 1 for user.
    private int authenticateSignIn(String userEmail, String password) { 
        if (accountsFetcher.getAdminAccount(userEmail)!=null)
            return 0; // admin

        if(accountsFetcher.getAccount(userEmail, password)!=null)
            return 1; // user
        
        return -1; // error
    }

    private boolean authenticateSignup(Account account){
        if(accountsFetcher.getAdminAccount(account.getUserEmail())!=null)
            return false;
        if(accountsFetcher.getAccount(account.getUserEmail())!=null)
            return false;
        return true;    
    }

     /**
     * signs in account if provided credentials are correct
     *
     * @param userEmail the email of the user
     * @param password the password of the user
     * @return returns user account if credential are for a user ---
               returns HttpStatus.Accepted if credentials are for an admin.
     * @throws IlegallAccessError if account does not exist
     */
    @GetMapping(value = "/sign-in")
    public ResponseEntity<Account> signIn(@RequestParam("userEmail") String userEmail,@RequestParam("password") String password){
        //System.out.println(userEmail+" "+password);

        int choice = authenticateSignIn(userEmail, password);
        switch(choice){
            case -1 : throw new IllegalAccessError("Wrong Credentials!"); // return error
            case 0 : return new ResponseEntity<>(null,HttpStatus.ACCEPTED);
            case 1 : return accountsFetcher.signIn(userEmail, password); // return user account
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
     /**
     * signs up and adds account to the system database 
     *
     * @param account the account that will be added to the systems
     * @return true if account is signed up and added succesfully, false otherwise
     * @throws IllegalArgumentException if account exists
     *
     */
    @PostMapping(value = "/sign-up")
    public ResponseEntity<Boolean> signUp(@RequestBody Account account) throws IllegalArgumentException {
        //System.out.println(account.getUserEmail()+" "+account.getPassword());
        
        // if account exists, return false because signup fails.
        if(!authenticateSignup(account)) 
            throw new IllegalArgumentException("Account Exists");

        account.init();
        accountsFetcher.signUp(account);
        return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
    }

}