package com.phase2.epayment.Controllers;

import org.springframework.stereotype.Component;

import com.phase2.epayment.ServicesDB.*;

@Component
public class DiscountController {
    private static double overAllDiscount = 0;
    private double[] servicesDiscounts = new double[Service.Names.values().length];

    private double discount = 0;

    DiscountController(){for(int i=0 ; i<servicesDiscounts.length ; i++) servicesDiscounts[i]=0;}

    public double getDiscount(String userName , String password, Service.Names serviceName){
        verifyOverAllDiscount(userName ,password);
        verifyServiceDiscount(serviceName);
        return discount;
    }

    public static double getOverAllDiscount(){return overAllDiscount;}
    public double getServiceDiscount(Service.Names serviceName){return servicesDiscounts[serviceName.ordinal()];}

    public void setServiceDiscount(Service.Names serviceName,double amount){servicesDiscounts[serviceName.ordinal()]=amount;}
    public static boolean setOverAllDiscount(double amount){if(amount>0){overAllDiscount = amount;return true;};return false;}

    private void verifyOverAllDiscount(String userName , String password){
        if(getAccount(userName,password).getTransactions().size()==0 && overAllDiscount>discount)
            discount=overAllDiscount; 
    }

    private void verifyServiceDiscount(Service.Names serviceName){
        if(servicesDiscounts[serviceName.ordinal()]> discount)
            discount=servicesDiscounts[serviceName.ordinal()];
    }
    
}