package com.phase2.epayment.ServicesDB;

import org.springframework.stereotype.Component;

@Component
public class Discount {
    
    private double serviceDiscount;
    private static double overAllDiscount;
    
    public Discount(double serviceDiscount, double overAllDiscount)
    {
        this.serviceDiscount = serviceDiscount;
        overAllDiscount = overAllDiscount;
    }

    public Discount(){
        this.serviceDiscount = 0;
        overAllDiscount = 0;
    }
    
    // getters
    public static double getOverAllDiscount() {return overAllDiscount;}
    public double getServiceDiscount() {return serviceDiscount;}
    
    // setters
    public void setServiceDiscount(double serviceDiscount) {this.serviceDiscount = serviceDiscount;}
    public static void setOverAllDiscount(double newOverallDiscount) {overAllDiscount = newOverallDiscount;}
}

