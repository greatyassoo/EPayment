package com.phase2.epayment.AccountsDB;

public class AdminAccount extends Throwable{
        protected String adminEmail;
        protected String adminPassword;
        AdminAccount(String adminEmail , String adminPassword){
            this.adminEmail = adminEmail;
            this.adminPassword = adminPassword;
        }
}
