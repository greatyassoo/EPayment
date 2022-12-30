package com.phase2.epayment.Payment;

import java.util.Map;

public interface PaymentType{
    public abstract boolean Pay(Map<String,String> data);
}