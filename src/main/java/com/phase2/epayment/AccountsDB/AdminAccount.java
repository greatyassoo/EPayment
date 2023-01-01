package com.phase2.epayment.AccountsDB;

public class AdminAccount{
    protected String adminEmail;
    protected String password;
    public AdminAccount(String adminEmail , String password){
        this.adminEmail = adminEmail;
        this.password = password;
    }

    public String getPassword(){return password;}
    public String getAdminEmail(){return adminEmail;}

}
