package com.phase2.epayment.ServicesDB;

import org.springframework.stereotype.Component;

@Component
public class Discount {
    
    private double discount;
    private static double overAllDiscount;
    
    public Discount(double discount, double overAllDiscount)
    {
        this.discount = discount;
        this.discount = overAllDiscount;
    }

    public Discount(){
        this.discount = 0;
        overAllDiscount = 0;
    }
    
    // getters
    public static double getOverAllDiscount() {return overAllDiscount;}
    public double getServiceDiscount() {return discount;}
    
    // setters
    public void setServiceDiscount(double discount) {this.discount = discount;}
    public static void setOverAllDiscount(double newOverallDiscount) {overAllDiscount = newOverallDiscount;}
}

