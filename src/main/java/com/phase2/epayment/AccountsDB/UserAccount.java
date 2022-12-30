package com.phase2.epayment.AccountsDB;

public class UserAccount extends Account{
    public UserAccount(String name,String password,String email, String phoneNumber){
        // super.name=name;
        // super.password=password;
        // super.email=email;
        // super.phoneNumber=phoneNumber;
        super(name, password, email,phoneNumber);
    }
}