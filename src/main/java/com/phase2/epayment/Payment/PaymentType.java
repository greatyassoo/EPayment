package com.phase2.epayment.Payment;

import java.util.LinkedList;

public interface PaymentType{
    public abstract boolean Pay(LinkedList<String> data);
}