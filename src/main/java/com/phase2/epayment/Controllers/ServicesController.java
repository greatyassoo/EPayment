package com.phase2.epayment.Controllers;

import java.util.LinkedList;

import com.phase2.epayment.AccountsDB.Account;
import com.phase2.epayment.AccountsDB.AccountsFetcher;
import com.phase2.epayment.ServicesDB.*;

public abstract class ServicesController {
    protected ServicesFetcher servicesFetcher;
    protected AccountsFetcher accountsFetcher;

    public LinkedList<ServiceProvider> getServiceProviders(String serviceName) {
        LinkedList<ServiceProvider> serviceProviders;
        try{
            Service service = servicesFetcher.getServices(serviceName).get(0);
            serviceProviders = service.getServiceProviders();
        }catch(Exception e){throw new IllegalAccessError("Error with serviceName");};
        return serviceProviders;
    }

    public Service getService(String serviceName) {
        try { return servicesFetcher.getServices(serviceName).get(0);}
        catch (Exception e) {}
        return null;
    }

    public Account getAccount(String userEmail, String password) {
        return accountsFetcher.getAccount(userEmail, password);
    }

}
