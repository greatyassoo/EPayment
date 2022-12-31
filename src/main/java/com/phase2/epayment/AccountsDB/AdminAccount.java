package com.phase2.epayment.AccountsDB;

public class AdminAccount{
    protected String adminEmail;
    protected String adminPassword;
    public AdminAccount(String adminEmail , String adminPassword){
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }

    public String getPassword(){return adminPassword;}
    public String getAdminEmail(){return adminEmail;}

}
