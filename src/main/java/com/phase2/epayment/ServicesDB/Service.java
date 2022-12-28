package com.phase2.epayment.ServicesDB;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;

public class Service {

    private String name;
    private LinkedList<ServiceProvider> serviceProviders;
    private Discount discount;

    Service(String name, LinkedList<ServiceProvider> serviceProviders){
        this.name = name;
        this.serviceProviders = serviceProviders;
        this.discount = new Discount();
    }
    @Autowired
    Service(String name, LinkedList<ServiceProvider> serviceProviders, Discount discount){
        this.name = name;
        this.serviceProviders = serviceProviders;
        this.discount = discount;
    }

    //setters
    public void setName(String name){this.name=name;}
    
    public boolean addServiceProvider(ServiceProvider serviceProvider){
        try {serviceProviders.addLast(serviceProvider);} 	
        catch (Exception e) {return false;}
        return true;
    }
    public void setDiscount(Discount discount) {this.discount = discount;}

    //getters
    public String getName() {return name;}
    public LinkedList<ServiceProvider> getServiceProviders(){return serviceProviders;}
    public Discount getDiscount() {return discount;}
}
