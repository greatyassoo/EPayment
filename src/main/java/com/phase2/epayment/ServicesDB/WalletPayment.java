package com.phase2.epayment.ServicesDB;

import java.util.LinkedList;

public class WalletPayment implements PaymentType{
    @Override
    public boolean Pay(LinkedList<String> data) {
        if(!(Double.parseDouble(data.getLast())>=Double.parseDouble(data.getFirst())))
            return false;
        return true;
    }  
}
