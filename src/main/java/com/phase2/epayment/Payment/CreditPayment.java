package com.phase2.epayment.Payment;

import java.util.Map;

public class CreditPayment implements PaymentType{
    @Override
    public boolean Pay(Map<String,String> data) {
        String CCN = data.get("CCN");
        String PIN = data.get("PIN");
        if(!CCN.matches("-?\\d+(\\.\\d+)?") || CCN.length()!=16 || PIN.length()!=4 || !PIN.matches("-?\\d+(\\.\\d+)?"))
            return false;
        return true;
    }
}
