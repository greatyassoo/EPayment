package com.phase2.epayment.Payment;

import java.util.Map;

public class WalletPayment implements PaymentType{
    @Override
    public boolean Pay(Map<String,String> data) {
        if(!(Double.parseDouble(data.get("walletBalance"))>=Double.parseDouble(data.get("amount"))))
            return false;
        return true;
    }  
}
