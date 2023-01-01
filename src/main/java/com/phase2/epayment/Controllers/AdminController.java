package com.phase2.epayment.Controllers;

import com.phase2.epayment.ServicesDB.*;
import com.phase2.epayment.AccountsDB.*;

import java.util.LinkedList;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/admin") 
public class AdminController extends ServicesController {

	AdminController(ServicesFetcher servicesFetcher, AccountsFetcher accountsFetcher){
		this.servicesFetcher = servicesFetcher;
		this.accountsFetcher = accountsFetcher;
	}

	/** done
	* add service provider to a specified service
	*
	* @param adminEmail admin email/user value
	* @param password admin password
	* @param serviceName the name of the service the provider is going to be added to.
	* @param serviceProviderName the name of the service provider to be added to the service.
	* @return true if service provider added successfully, false otherwise
	* @throws IllegalAccessError if admin credentials are incorrect
	*
	* @note the parameters mentioned above are held in the argument "body" which is a map that holds key value pairs
    * of n parameters. The key is the parameter name and the value corresponds to the paremeter value. 
    * ie: "serviceName": "Mobile Recharge Service" 	
	*/
	@PostMapping(value = "/service-provider")
	public boolean addServiceProvider(@RequestBody Map<String, String> body) {
		String adminEmail = body.get("adminEmail");
		String password = body.get("password");
		
		if(! checkAdminAccount(adminEmail, password)){
			throw new IllegalAccessError("admin account not valid");
		}

		String serviceName = body.get("serviceName");
		String serviceProviderName = body.get("serviceProviderName");

		System.out.println(serviceName+" "+serviceProviderName);

		try {
			if(getService(serviceName)==null) return false;

			if(checkServiceProviderNames(serviceName,serviceProviderName)){
				System.out.println("Service provider alreadty exists!");
				return false;
			}
			getService(serviceName).addServiceProvider(new ServiceProvider(serviceProviderName));
			return true;
		}
		catch (Exception e) {return false;}
	}
	
	/** done
	* returns all user accounts saved in the system database
	*
	* @param adminEmail admin email/user value
	* @param password admin password
	* @return accounts saved in the system.
	* @throws IllegalAccessError if admin credentials are incorrect
	*/
	@GetMapping(value = "/all-accounts")
	public LinkedList<Account> getAllAccounts(@RequestParam("adminEmail") String adminEmail ,
			@RequestParam("password") String password) {
		if(! checkAdminAccount(adminEmail, password))
			throw new IllegalAccessError("admin account not valid");
		return accountsFetcher.getAllAccounts();
	}

	/** done
	* returns all specified user transactions 
	*
	* @param adminEmail admin email/user value
	* @param password admin password
	* @param userEmail email of the account to be searched
	* @return transactions of specified user account
	* @throws IllegalAccessError if admin credentials are incorrect
	* @throws IllegalAccessError if account does not exist
	*
	*/
	@GetMapping(value = "/user-transactions")
	public LinkedList<Transaction> getAllAccountTransactions(@RequestParam("adminEmail") String adminEmail ,
			@RequestParam("password") String password
			,@RequestParam("userEmail") String userEmail){	
		if(!checkAdminAccount(adminEmail, password))
			throw new IllegalAccessError("admin account not valid");

		try {
			Account account = accountsFetcher.getAccount(userEmail);
			return account.getAllTransactions();	
		} 
		catch (Exception e) {throw new IllegalAccessError("Account doesn't exist.");}
	}

	/** done
	* returns all pending refund requests
	*
	* @param adminEmail admin email/user value
	* @param password admin password
	* @return refund requests issued by all the users in the system.
	* @throws IllegalAccessError if admin credentials are incorrect
	*
	*/
	@GetMapping(value = "/refund-requests")
	public LinkedList<LinkedList<String>> getRefundRequests(@RequestParam("adminEmail") String adminEmail ,
			@RequestParam("password") String password){
		if(! checkAdminAccount(adminEmail, password))
			throw new IllegalAccessError("Admin account not valid");

		LinkedList<Account> accounts = accountsFetcher.getAllAccounts();
		LinkedList<LinkedList<String>> refundRequests = new LinkedList<LinkedList<String>>();
		for(int i=0 ; i<accounts.size() ; i++){
			for(int j=0 ; j<accounts.get(i).getRefundRequests().size() ; j++){
				int indx = accounts.get(i).getRefundRequests().get(j);
				Transaction transaction = accounts.get(i).getTransaction(indx);
				LinkedList<String> temp = new LinkedList<String>();
				temp.addLast(String.valueOf(transaction.getTransactionID()));
				temp.addLast(accounts.get(i).getUserEmail());
				temp.addLast(transaction.getService());
				temp.addLast(Double.toString(transaction.getAmount()));
				refundRequests.addLast(temp);
			}
		}
		return refundRequests;
	}

	/** done
	* a function where the admin chooses to accept or reject a specific account's refund request
	*
	* @param adminEmail admin email/user value
	* @param password admin password
	* @param userEmail email of the account that issued the requests
	* @param answer the choice of wether to 'accept' ,'reject', or 'cancel'.
	* @param transactionID the id of the transaction
	* @return 0: if transaction added succesfuly
	*		 -1: if answer invalid
	*		 -2: if transaction couldn't be found
	*		 -3: if general error occurred
	*		 -4: if answer is 'cancel'
	* @throws IllegalAccessError if admin credentials are incorrect
	*
	* @note the parameters mentioned above are held in the argument "body" which is a map that holds key value pairs
    * of n parameters. The key is the parameter name and the value corresponds to the paremeter value. 
    * ie: "transactionID": "4"
	*/
	@PostMapping(value = "/refund-requests")
	public int processRefundRequest(@RequestBody Map<String,String> body) {
		String adminEmail = body.get("adminEmail");
		String password = body.get("password");
		
		if(! checkAdminAccount(adminEmail, password)){
			throw new IllegalAccessError("admin account not valid");
		}

		String answer = body.get("answer");
		if( answer.toLowerCase().equals("cancel"))
			return -4;
		if(!answer.toLowerCase().equals("accept") && !answer.toLowerCase().equals("reject"))
			return -1;

		String userEmail = body.get("userEmail");
		int transactionID = Integer.parseInt(body.get("transactionID"));
		Account account = accountsFetcher.getAccount(userEmail);
		if(account==null)
			return -2;
			
		Transaction transaction = account.getTransaction(transactionID);
		if(transaction==null)
			return -2;

		if(answer.toLowerCase().equals("reject")){
			account.addTransaction(new Transaction(Transaction.TYPE.REFUND_REJECTED, "Refund", "Admin",
				"", "", 0, 0));
			account.removeRefundRequest(transactionID);
		}
		else if(answer.toLowerCase().equals("accept")){
			account.addTransaction(new Transaction(Transaction.TYPE.REFUND_ACCEPTED, "Refund", "Admin",
				"", "", -transaction.getAmount(), 0));
			account.setWalletBalance(account.getWalletBalance()+transaction.getAmount());
			account.removeRefundRequest(transactionID);
		}
		else
			return -3;

		return 0;
	}

	/** done
	* a function where an admin adds a new admin account
	*
	* @param adminEmail admin email/user value
	* @param password admin password
	* @param newAdminEmail new admin email/user
	* @param newPassword new admin password
	* @return true if admin account succesfully added, false if NEW admin email already exists
	* @throws IllegalAccessError if admin credentials are incorrect
	*
	* @note the parameters mentioned above are held in the argument "body" which is a map that holds key value pairs
    * of n parameters. The key is the parameter name and the value corresponds to the paremeter value. 
    * ie: "transactionID": "4" 	
	*/
	@PostMapping("/account")
	public boolean registerAdmin(@RequestBody Map<String,String> body){
		String adminEmail = body.get("adminEmail");
		String password = body.get("password");
		String newAdminEmail = body.get("newAdminEmail");
		String newPassword = body.get("newPassword");

		if(! checkAdminAccount(adminEmail, password))
			throw new IllegalAccessError("admin account not valid");

		if(accountsFetcher.getAdminAccount(adminEmail)!=null)
			return false;
		
		accountsFetcher.addAdminAccount(new AdminAccount(newAdminEmail,newPassword));
		return true;
	}
	
	private boolean checkServiceProviderNames(String serviceName,String serviceProviderName){
		LinkedList <ServiceProvider> temp = getService(serviceName).getServiceProviders();
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).getName().toLowerCase().equals(serviceProviderName.toLowerCase()))
				return true;
		}
		return false;
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
