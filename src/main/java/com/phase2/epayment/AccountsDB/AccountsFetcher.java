package com.phase2.epayment.AccountsDB;

import java.util.LinkedList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountsFetcher implements AccountAuthentication{
    private LinkedList<Account> accounts;

    AccountsFetcher(LinkedList<Account> accounts){
        this.accounts = accounts;
        accounts.addFirst(new Account("test","test"));
        accounts.getFirst().setEmail("faristroller@fci.cringe");
    }

    public ResponseEntity<Account> login(String uName , String password){ 
        for(int i=0 ; i<accounts.size() ; i++){
            if(accounts.get(i).getUserName().equals(uName) && accounts.get(i).getPassword().equals(password)){
                return new ResponseEntity<>(accounts.get(i),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Boolean> signUp(Account account){
        accounts.add(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Account getAccount(int index) {
        return accounts.get(index);
    }

    public Account getAccount(String userName, String password){
        Account temp=null;
        for(int i=0 ; i<accounts.size() ; i++){
            if(accounts.get(i).getUserName().equals(userName) && accounts.get(i).getPassword().equals(password)){
                accounts.addFirst(accounts.remove(i));
                temp=accounts.get(i);
                break;
            }
        }
        return temp;
    }

    public Account getAccount(String userName){
        Account temp=null;
        for(int i=0 ; i<accounts.size() ; i++){
            if(accounts.get(i).getUserName().equals(userName)){
                accounts.addFirst(accounts.remove(i));
                temp=accounts.get(i);
                break;
            }
        }
        return temp;
    }

    public LinkedList<Account> getAllAccounts(){
        return this.accounts;
    }

    public int getSize() {
        try {return accounts.size();}
        catch (Exception e) {return 0;}
    }
}
