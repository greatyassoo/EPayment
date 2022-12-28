package com.phase2.epayment.ServicesDB;

import java.util.LinkedList;

public class CashPayment implements PaymentType{
    @Override
    public boolean Pay(LinkedList<String> data) {
        System.out.println("Payment Done Using Cash On Delivery amount = " + data.get(0));
        return true;
    }
}
