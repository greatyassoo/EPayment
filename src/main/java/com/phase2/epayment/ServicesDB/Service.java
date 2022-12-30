package com.phase2.epayment.ServicesDB;
import java.util.LinkedList;

import com.phase2.epayment.Payment.PaymentType;

public class Service {

    private String name;
    private LinkedList<ServiceProvider> serviceProviders;
    private Discount discount;
    private LinkedList<PaymentType> acceptedPayment ;

    Service(String name, LinkedList<ServiceProvider> serviceProviders, Discount discount, LinkedList<PaymentType> acceptedPayment){
        this.name = name;
        this.serviceProviders = serviceProviders;
        this.discount = discount;
        this.acceptedPayment = acceptedPayment;
    }

    //setters
    public void setName(String name){this.name=name;}
    public void addPaymentType(PaymentType paymentType){
        if(!acceptedPayment.contains(paymentType))
            acceptedPayment.addLast(paymentType);
    }
    public void removePaymentType(PaymentType paymentType){
        if(acceptedPayment.contains(paymentType))
            acceptedPayment.remove(paymentType);
    }
    public boolean addServiceProvider(ServiceProvider serviceProvider){
        try {serviceProviders.addLast(serviceProvider);} 	
        catch (Exception e) {return false;}
        return true;
    }
    public void setDiscount(Discount discount) {this.discount = discount;}

    //getters
    public String getName() {return name;}
    public LinkedList<ServiceProvider> getServiceProviders(){return serviceProviders;}
    public LinkedList<PaymentType> getPaymentTypes(){return acceptedPayment;}
    public Discount getDiscount() {return discount;}
}
