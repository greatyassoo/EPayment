package com.phase2.epayment.AccountsDB;

import java.util.LinkedList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountsFetcher implements AccountAuthentication{

    private LinkedList<Account> accounts;
    private LinkedList<AdminAccount> adminAccounts = new LinkedList<>();

    AccountsFetcher(LinkedList<Account> accounts){
        this.accounts = accounts;
        adminAccounts.addLast(new AdminAccount("admin","admin"));
    }

    public ResponseEntity<Account> signIn(String userEmail , String password){ 
        for(int i=0 ; i<accounts.size() ; i++){
            if(accounts.get(i).getUserEmail().equals(userEmail) && accounts.get(i).getPassword().equals(password)){
                return new ResponseEntity<>(accounts.get(i),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Boolean> signUp(Account account){
        accounts.add(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Account getAccount(String userEmail, String password){
        Account temp=null;
        for(int i=0 ; i<accounts.size() ; i++){
            if(accounts.get(i).getUserEmail().equals(userEmail) && accounts.get(i).getPassword().equals(password)){
                accounts.addFirst(accounts.remove(i));
                temp=accounts.get(i);
                break;
            }
        }
        return temp;
    }

    public Account getAccount(String userEmail){
        Account temp=null;
        for(int i=0 ; i<accounts.size() ; i++){
            if(accounts.get(i).getUserEmail().equals(userEmail)){
                accounts.addFirst(accounts.remove(i));
                temp=accounts.get(i);
                break;
            }
        }
        return temp;
    }

    public AdminAccount getAdminAccount(String adminEmail) {
        AdminAccount temp=null;
        for(int i=0 ; i<adminAccounts.size() ; i++){
            if(adminAccounts.get(i).adminEmail.equals(adminEmail)){
                temp=adminAccounts.get(i);
                break;
            }
        }
        return temp;
    }

    public void addAdminAccount(AdminAccount adminAccount){adminAccounts.addLast(adminAccount);}

    public LinkedList<Account> getAllAccounts(){return this.accounts;}
}
