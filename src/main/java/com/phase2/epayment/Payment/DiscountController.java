package com.phase2.epayment.Payment;



import java.util.LinkedList;



import com.phase2.epayment.ServicesDB.*;
import com.phase2.epayment.AccountsDB.AccountsFetcher;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/discount")
public class DiscountController {
    private AccountsFetcher accountsFetcher;
    private ServicesDB servicesDB;



    DiscountController(AccountsFetcher accountsFetcher, ServicesDB servicesDB) {
        this.accountsFetcher = accountsFetcher;
        this.servicesDB = servicesDB;
    }

    /**
     * 
     * @return overall discount if user has NO transactions, service discount otherwise.
     * @throws IlegallAccessError if service OR account do not exist
     */
    @GetMapping("/user-discount")
    public double getUserDiscount(@RequestParam("userEmail") String userEmail, @RequestParam("password") String password,
            @RequestParam("serviceName") String serviceName) {
                return verifyOverallDiscount(userEmail, password, serviceName);
    }

    @GetMapping(value = "/overall-discount")
    public static double getOverAllDiscount() {
        return Discount.getOverAllDiscount();
    }


    @PostMapping("/overall-discount")
    public static void setOverAllDiscount(@RequestParam("amount") double amount) {
        if (amount < 0) 
            throw new IllegalArgumentException("Invalid value has been entered");
        Discount.setOverAllDiscount(amount);
    }

    @GetMapping(value = "/service-discount")
    public double getServiceDiscount(@RequestParam("serviceName") String serviceName) throws Exception {
        LinkedList<Service> services = servicesDB.getAllServices();

        for (int i = 0; i < services.size(); i++) {
            if (services.get(i).getName().equals(serviceName))
                return services.get(i).getDiscount().getServiceDiscount();
        }
        throw new IllegalAccessError("Service does not exist\n");

    }

    @PostMapping(value = "/service-discount")
    public void setServiceDiscount(@RequestParam("serviceName") String serviceName,
            @RequestParam("amount") double amount) {
            LinkedList<Service> services = servicesDB.getAllServices();
            for (int i = 0; i < services.size(); i++) {
                if (services.get(i).getName().equals(serviceName)){
                    services.get(i).getDiscount().setServiceDiscount(amount);
                    return;
                }
            }
        throw new IllegalAccessError("Service does not exist\n");
    }

   

    private double verifyOverallDiscount(String userEmail, String password, String serviceName) throws IllegalAccessError {
        // check if account exists.
        if ((accountsFetcher.getAccount(userEmail, password) == null))
            throw new IllegalAccessError("Account does not exist");
        
        LinkedList<Service> services = servicesDB.getAllServices();

        boolean foundService = false;
        int i = 0;
        for (i = 0; i < services.size(); i++) {
            if (services.get(i).getName().equals(serviceName)){
                foundService = true;
                break;   
            }
        }
        // if service not found
        if (!foundService)
            throw new IllegalAccessError("Service does not exist");

        if (accountsFetcher.getAccount(userEmail, password).getTransactions().size() == 0 && Discount.getOverAllDiscount() > servicesDB.get(i).getDiscount().getServiceDiscount())
            return Discount.getOverAllDiscount();
        else
            return servicesDB.get(i).getDiscount().getServiceDiscount();
    }
}