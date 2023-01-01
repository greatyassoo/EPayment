package com.phase2.epayment.Controllers;

import java.util.LinkedList;
import java.util.Map;

import com.phase2.epayment.AccountsDB.AccountsFetcher;
import com.phase2.epayment.ServicesDB.Discount;
import com.phase2.epayment.ServicesDB.Service;
import com.phase2.epayment.ServicesDB.ServicesFetcher;
import com.phase2.epayment.AccountsDB.AdminAccount;

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
    private ServicesFetcher servicesFetcher;

    DiscountController(AccountsFetcher accountsFetcher, ServicesFetcher servicesFetcher) {
        this.accountsFetcher = accountsFetcher;
        this.servicesFetcher = servicesFetcher;
    }

    /**
    * checks and returns specified user applicable discount
    * 
    * @param userEmail the email of the user
    * @param password the password of the user
    * @param serviceName the name of the service 
    * @return overall discount if user has NO transactions, service discount otherwise.
    * @throws IlegallAccessError if service OR account do not exist
    */
    @GetMapping("/user-discount")
    public double getUserDiscount(@RequestParam("userEmail") String userEmail, 
            @RequestParam("password") String password,
            @RequestParam("serviceName") String serviceName) {
                return verifyOverallDiscount(userEmail, password, serviceName);
    }


    /**
    * sets the overall discount with given amount
    *
    * @param adminEmail admin email/user value
	* @param password admin password
    * @param amount takes the key "amount" which holds overall discount value
    * @throws IllegalArgumentException if amount value provided is less than zero
    * @throws IllegalAccessError if admin credentials are incorrect
    *
    * @note the parameters mentioned above are held in the argument "body" which is a map that holds key value pairs
    * of n parameters. The key is the parameter name and the value corresponds to the paremeter value. 
    * ie: "serviceName": "Mobile Recharge Service" 
    */
    @PostMapping("/overall-discount")
    public void setOverAllDiscount(@RequestBody Map<String,String> body) {
        String adminEmail = body.get("adminEmail");
        String password = body.get("password");

        if(! checkAdminAccount(adminEmail, password)){
            throw new IllegalAccessError("admin account not valid");
        }

        double amount = Double.parseDouble(body.get("amount"));

        if (amount < 0) 
            throw new IllegalArgumentException("Invalid amount value has been entered");
        Discount.setOverAllDiscount(amount);
    }

    /**
    * returns service discount of the provided service
    *
    * @param serviceName the name of the service that discount will be returned
    * @throws IllegalAccessError if provided service name does not exist
    * 
    */
    @GetMapping(value = "/service-discount")
    public Discount getServiceDiscount(@RequestParam("serviceName") String serviceName) {
        
        LinkedList<Service> services = servicesFetcher.getServices(serviceName);

        if(services!=null)
            return services.get(0).getDiscount();
        
        throw new IllegalAccessError("Service does not exist\n");
    }



    /**
    * sets the service discount of the given service with given amount
    *
    * @param adminEmail admin email/user value
	* @param password admin password
    * @param serviceName the name of the service that discount will be set
    * @param amount new service discount value
    * @throws IllegalAccessError if provided service name does not exist
    * @throws IllegalArgumentException if amount value provided is less than zero
    * @throws IllegalAccessError if admin credentials are incorrect
    *
    *
    * @note the parameters mentioned above are held in the argument "body" which is a map that holds key value pairs
    * of n parameters. The key is the parameter name and the value corresponds to the parameter value. 
    * ie: "serviceName": "Mobile Recharge Service" 
    */
    @PostMapping(value = "/service-discount")
    public void setServiceDiscount(@RequestBody Map<String,String> body) {
                String adminEmail = body.get("adminEmail");
                String password = body.get("password");

                if(! checkAdminAccount(adminEmail, password)){
                    throw new IllegalAccessError("admin account not valid");
                }

                String serviceName = body.get("serviceName");
                double amount = Double.parseDouble(body.get("amount"));

                if (amount < 0) 
                    throw new IllegalArgumentException("Invalid amount value has been entered");

                LinkedList<Service> services = servicesFetcher.getServices("");
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
        
        Service service ;
        try{service = servicesFetcher.getServices(serviceName).get(0);}
        catch(Exception e ){throw new IllegalAccessError("Error with service name");}
        

        if (accountsFetcher.getAccount(userEmail, password).getAllTransactions().size() == 0 && 
            Discount.getStaticOverAllDiscount() < service.getDiscount().getServiceDiscount())
            return Discount.getStaticOverAllDiscount();
        else
            return service.getDiscount().getServiceDiscount();
    }

	private boolean checkAdminAccount(String adminEmail , String password){
		AdminAccount adminAccount = accountsFetcher.getAdminAccount(adminEmail);
		if(adminAccount==null)
			return false;
		if(! adminAccount.getPassword().equals(password))
			return false;
		return true;
	}    
}