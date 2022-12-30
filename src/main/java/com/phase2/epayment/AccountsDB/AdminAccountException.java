package com.phase2.epayment.AccountsDB;

public class AdminAccountException extends Exception {
    AdminAccountException(AdminAccount adminAccount){
        super(adminAccount);
    }
}
