package com.phase2.epayment.Controllers;

import java.util.LinkedList;

import com.phase2.epayment.AccountsDB.Account;
import com.phase2.epayment.AccountsDB.AccountsFetcher;
import com.phase2.epayment.ServicesDB.*;

public abstract class ServicesController {
    protected ServicesDB servicesDB;
    protected AccountsFetcher accountsFetcher;

    public LinkedList<ServiceProvider> getServiceProviders(String serviceName) {
        LinkedList<ServiceProvider> temp = new LinkedList<ServiceProvider>();
        for (int i = 0; i < servicesDB.size(); i++) 
        {
            if (servicesDB.get(i).getName().toLowerCase().contains(serviceName.toLowerCase())) {
                temp = servicesDB.get(i).getServiceProviders();
            }
        }
        return temp;
    }

    public Service getService(String serviceName) {
        try {
            for (int index = 0; index < servicesDB.size(); index++) {
                if(servicesDB.get(index).getName().equals(serviceName))
                    return servicesDB.get(index);
            }
        } catch (Exception e) {}
        return null;
    }

    public Account getAccount(String userEmail, String password) {
        return accountsFetcher.getAccount(userEmail, password);
    }

}