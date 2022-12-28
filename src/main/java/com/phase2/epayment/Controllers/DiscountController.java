package com.phase2.epayment.Controllers;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import com.phase2.epayment.ServicesDB.*;
import com.phase2.epayment.AccountsDB.AccountsFetcher;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@RestController
@RequestMapping("/discount")
public class DiscountController {
    private static double overAllDiscount = 0;
    private double discount = 0;
    private AccountsFetcher accountsFetcher;
    private ServicesDB servicesDB;

    DiscountController(AccountsFetcher accountsFetcher, ServicesDB servicesDB) {
        this.accountsFetcher = accountsFetcher;
        this.servicesDB = servicesDB;
    }

    public double getDiscount(@RequestParam("userName") String userName, @RequestParam("password") String password,
            @RequestParam("serviceName") String serviceName) {
        verifyOverAllDiscount(userName, password);
        verifyServiceDiscount(serviceName);
        return discount;
    }

    @GetMapping(value = "/over-all-discount")
    public static double getOverAllDiscount() {
        return overAllDiscount;
    }

    @GetMapping(value = "/service-discount")
    public double getServiceDiscount(@RequestParam("serviceName") String serviceName) throws Exception {
        LinkedList<Service> services = servicesDB.getAllServices();

        for (int i = 0; i < services.size(); i++) {
            if (services.get(i).getName().equals(serviceName))
                return servicesDiscounts[i];
        }
        throw new IllegalAccessError("Service does not exist\n");

    }

    @PostMapping(value = "/new-service-discount")
    public String setServiceDiscount(@RequestParam("serviceName") String serviceName,
            @RequestParam("amount") double amount) {

        try {
            LinkedList<Service> services = servicesDB.getAllServices();
            for (int i = 0; i < services.size(); i++) {
                if (services.get(i).getName().equals(serviceName)) {
                    servicesDiscounts[i] = amount;
                    return "Discount Added.";
                }
            }
        } 
        catch (Exception e) { throw new IllegalAccessError("Service does not exist."); }
        return null;
    }

    @PostMapping("new-over-all-discount")
    public static boolean setOverAllDiscount(@RequestParam("amount") double amount) {
        if (amount >= 0) {
            overAllDiscount = amount;
            return true;
        }
        return false;
    }

    private void verifyOverAllDiscount(String userName, String password) {
        if (accountsFetcher.getAccount(userName, password).getTransactions().size() == 0 && overAllDiscount > discount)
            discount = overAllDiscount;
    }

    private void verifyServiceDiscount(Service.Names serviceName) {
        if (servicesDiscounts[serviceName.ordinal()] > discount)
            discount = servicesDiscounts[serviceName.ordinal()];
    }

}