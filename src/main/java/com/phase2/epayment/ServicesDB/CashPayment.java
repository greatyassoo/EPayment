package com.phase2.epayment.ServicesDB;

public class CashPayment implements PaymentType{
    @Override
    public boolean Pay(String amount) {
        System.out.println("Payment Done Using Cash On Delivery amount = "+amount);
        return true;
    }
}
