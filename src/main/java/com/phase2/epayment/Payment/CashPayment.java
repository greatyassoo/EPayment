package com.phase2.epayment.Payment;

import java.util.Map;

public class CashPayment implements PaymentType{
    @Override
    public boolean Pay(Map<String,String> data) {
        System.out.println("Payment Done Using Cash On Delivery amount = " + data.get("amount"));
        return true;
    }
}
