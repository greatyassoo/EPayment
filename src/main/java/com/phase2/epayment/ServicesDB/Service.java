package com.phase2.epayment.ServicesDB;
import java.util.LinkedList;

public class Service {

    public static enum Names {MobileRecharge,Landline,InternetRecharge,Donations};
    private String name;
    private LinkedList<ServiceProvider> serviceProviders;

    Service(String name, LinkedList<ServiceProvider> serviceProviders){
        this.name = name;
        this.serviceProviders = serviceProviders;
    }

    //setters
    public void setName(String name){this.name=name;}
    
    public boolean addServiceProvider(ServiceProvider serviceProvider){
        try {serviceProviders.addLast(serviceProvider);} 	
        catch (Exception e) {return false;}
        return true;
    }

    //getters
    public String getName() {return name;}
    public LinkedList<ServiceProvider> getServiceProviders(){return serviceProviders;}

}
