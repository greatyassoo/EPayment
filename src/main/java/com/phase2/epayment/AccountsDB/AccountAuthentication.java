package com.phase2.epayment.AccountsDB;

import org.springframework.http.ResponseEntity;

public interface AccountAuthentication{
    public ResponseEntity<Account> signIn(String userEmail, String password);
    public ResponseEntity<Boolean> signUp(Account account);
}