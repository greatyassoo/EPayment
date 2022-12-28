package com.phase2.epayment.ServicesDB;

import java.util.LinkedList;

public interface PaymentType{
    public abstract boolean Pay(LinkedList<String> data);
}