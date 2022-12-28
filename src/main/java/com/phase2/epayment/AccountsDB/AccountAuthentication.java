package com.phase2.epayment.AccountsDB;

import org.springframework.http.ResponseEntity;

public interface AccountAuthentication{
    public ResponseEntity<Account> login(String userName, String password);
    public ResponseEntity<Boolean> signUp(Account account);
}