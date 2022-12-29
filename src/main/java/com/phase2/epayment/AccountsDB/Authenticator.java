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
    public static final String ADMIN_NAME = "admin", ADMIN_PASSWORD = "admin";

    Authenticator(AccountsFetcher accountsFetcher){
        this.accountsFetcher=accountsFetcher;
    }

    private int authenticatesignIn(String userEmail, String password) { // -1 for error, 0 for admin, 1 for user.
        if (userEmail.equals(ADMIN_NAME) && password.equals(ADMIN_PASSWORD))
            return 0; // admin

        for(int i = 0; i < accountsFetcher.getSize(); i++){
            if(accountsFetcher.getAccount(i).getUserEmail().equals(userEmail) && accountsFetcher.getAccount(i).getPassword().equals(password))
                return 1; // user
        }
        return -1; // error
    }

    private boolean authenticateSignup(Account account){
        for(int i = 0; i < accountsFetcher.getSize(); i++){
            if(account.getUserEmail().equals(ADMIN_NAME) || 
            accountsFetcher.getAccount(i).getUserEmail().equals(account.getUserEmail()) || 
            accountsFetcher.getAccount(i).getUserName().equals(account.getUserName()))
                return false;
        }
        return true;
    }

    @GetMapping(value = "/sign-in")
    public ResponseEntity<Account> signIn(@RequestParam("userEmail") String userEmail,@RequestParam("password") String password)throws IllegalAccessError {
        //System.out.println(userEmail+" "+password);

        int choice = authenticatesignIn(userEmail, password);
        switch(choice){
            case -1 : throw new IllegalAccessError("Wrong Credentials!"); // return error
            case 0 : return new ResponseEntity<>(new Account(ADMIN_NAME, ADMIN_PASSWORD),HttpStatus.OK); // return admin account
            case 1 : return accountsFetcher.signIn(userEmail, password); // return user account
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }   

    @PostMapping("/sign-up")
    public ResponseEntity<Boolean> signUp(@RequestBody Account account) throws IllegalThreadStateException {
        //System.out.println(account.getUserEmail()+" "+account.getPassword());
        
        // if account exists, return false because signup fails.
        if(!authenticateSignup(account)) 
            throw new IllegalStateException("Account Exists");

        accountsFetcher.signUp(account);
        return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
    }

}