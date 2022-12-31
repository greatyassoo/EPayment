package com.phase2.epayment.Payment;



import java.util.LinkedList;
import java.util.Map;

import com.phase2.epayment.ServicesDB.*;
import com.phase2.epayment.AccountsDB.AccountsFetcher;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * @param userEmail the email of the user
     * @param password the password of the user
     * @param serviceName the name of the service 
     * @return overall discount if user has NO transactions, service discount otherwise.
     * @throws IlegallAccessError if service OR account do not exist
     */
    @GetMapping("/user-discount")
    public double getUserDiscount(@RequestParam("userEmail") String userEmail, @RequestParam("password") String password,
            @RequestParam("serviceName") String serviceName) {
                return verifyOverallDiscount(userEmail, password, serviceName);
    }


     /**
     * @return current overall discount
     */
    @GetMapping(value = "/overall-discount")
    public double getOverAllDiscount() {
        return Discount.getStaticOverAllDiscount();
    }

    /**
    * sets the overall discount with given amount
    *
    * @param amount takes the key "amount" which holds overall discount value
    * @throws IllegalArgumentException if amount value provided is less than zero
    *
    * @note the parameters mentioned above are held in the argument "body" which is a map that holds key value pairs
    * of n parameters. The key is the parameter name and the value corresponds to the paremeter value. 
    * ie: "serviceName": "Mobile Recharge Service" 
    */
    @PostMapping("/overall-discount")
    public static void setOverAllDiscount(@RequestBody Map<String,String> body) {
        double amount = Double.parseDouble(body.get("amount"));

        if (amount < 0) 
            throw new IllegalArgumentException("Invalid amount value has been entered");
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


    /**
    * sets the service discount of the given service discount with given amount
    *
    * @param serviceName the name of the service that discount will be set
    * @param amount new service discount value
    * @throws IllegalAccessError if provided service name does not exist
    * @throws IllegalArgumentException if amount value provided is less than zero
    *
    * @note the parameters mentioned above are held in the argument "body" which is a map that holds key value pairs
    * of n parameters. The key is the parameter name and the value corresponds to the paremeter value. 
    * ie: "serviceName": "Mobile Recharge Service" 
    */
    @PostMapping(value = "/service-discount")
    public void setServiceDiscount(@RequestBody Map<String,String> body) {
                String serviceName = body.get("serviceName");
                 double amount = Double.parseDouble(body.get("amount"));

                if (amount < 0) 
                    throw new IllegalArgumentException("Invalid amount value has been entered");

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

        if (accountsFetcher.getAccount(userEmail, password).getTransactions().size() == 0 && Discount.getStaticOverAllDiscount() > servicesDB.get(i).getDiscount().getServiceDiscount())
            return Discount.getStaticOverAllDiscount();
        else
            return servicesDB.get(i).getDiscount().getServiceDiscount();
    }
}