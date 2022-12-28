package com.phase2.epayment.ServicesDB;
import java.util.LinkedList;
public interface ServiceFormFactory{
    public Service createService(LinkedList<ServiceProvider> serviceProviders);
}