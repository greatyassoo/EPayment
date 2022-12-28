package com.phase2.epayment.ServicesDB;

import java.util.LinkedList;

public class CreditPayment implements PaymentType{
    @Override
    public boolean Pay(LinkedList<String> data) {
        String CCN = data.get(0);
        String PIN = data.get(1);
        if(!CCN.matches("-?\\d+(\\.\\d+)?") || CCN.length()!=16 || PIN.length()!=4 || !PIN.matches("-?\\d+(\\.\\d+)?"))
            return false;
        return true;
    }
}
