package com.phase2.epayment.Payment;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public abstract class PaymentType{
    protected String type;
    public abstract boolean Pay(Map<String,String> data);
    public String getType(){
        return this.getClass().getSimpleName();
    }
}