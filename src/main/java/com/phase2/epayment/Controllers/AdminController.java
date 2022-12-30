package com.phase2.epayment.Controllers;

import com.phase2.epayment.ServicesDB.*;
import com.phase2.epayment.AccountsDB.*;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/admin") // this means any mapping inside this class starts with "/admin"
public class AdminController extends ServicesController {
	private AccountsFetcher accountsFetcher;

	AdminController(ServicesDB servicesDB, AccountsFetcher accountsFetcher){
		this.servicesDB = servicesDB;
		this.accountsFetcher = accountsFetcher;
	}

	/**
     * 
     * @param serviceName the name of the service the provider is going to be added to.
	 * @param serviceProviderName the name of the serviceProvider to be added to the service.
     */
	@PostMapping(value = "/service-provider")
	public boolean addServiceProvider(@RequestBody Map<String, String> body) {
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
	

	@GetMapping(value = "/all-accounts")
	public LinkedList<Account> getAllAccounts() {
		return accountsFetcher.getAllAccounts();
	}

	@GetMapping(value = "/user-transaction")
	public LinkedList<Transaction> getAllAccountTransactions(@RequestParam("userEmail") String userEmail){	
		try {
			Account account = accountsFetcher.getAccount(userEmail);
			LinkedList<Transaction> transactions = account.getTransactions();	
			return transactions;
		} 
		catch (Exception e) {return null;}
	}

	@GetMapping(value = "/refund-request")
	public LinkedList<LinkedList<String>> getRefundRequests(){
		LinkedList<Account> accounts = accountsFetcher.getAllAccounts();
		LinkedList<LinkedList<String>> refundRequests = new LinkedList<LinkedList<String>>();
		for(int i=0 ; i<accounts.size() ; i++){
			for(int j=0 ; j<accounts.get(i).getRefundRequests().size() ; j++){
				int indx = accounts.get(i).getRefundRequests().get(j);
				Transaction transaction = accounts.get(i).getTransaction(indx);
				LinkedList<String> temp = new LinkedList<String>();
				temp.addLast(accounts.get(i).getUserEmail());
				temp.addLast(transaction.getService());
				temp.addLast(Double.toString(transaction.getAmount()));
				refundRequests.addLast(temp);
			}
		}
		return refundRequests;
	}

	//0 transaction added successfully
	//-1 answer invalid
	//-2 transaction couldn't be found
	//-3 general error
	//-4 cancel

	
	@PostMapping(value = "/refund-request")
	public int processRefundRequest(@RequestBody Map<String,String> body) {
		String answer = body.get("answer");
		if( answer.toLowerCase().equals("cancel"))
			return -4;
		if(!answer.toLowerCase().equals("accept") && !answer.toLowerCase().equals("reject"))
			return -1;

		String userEmail = body.get("userEmail");
		String service = body.get("service");
		double amount = Double.parseDouble(body.get("amount"));
		Account account = accountsFetcher.getAccount(userEmail);
		
		if(account==null)
			return -2;
		
		int transactionIndx = -1;
		int refundIndx = -1;
		
		for(int i=0 ; i<account.getRefundRequests().size() ; i++){
			if(account.getTransaction(account.getRefundRequests().get(i)).getService().equals(service) 
				&& account.getTransaction(account.getRefundRequests().get(i)).getAmount() == amount){
				transactionIndx = account.getRefundRequests().get(i);
				refundIndx = i;
				break;
			}
		}

		if(answer.toLowerCase().equals("reject")&&transactionIndx!=-1){
			account.addTransaction(new Transaction(Transaction.TYPE.REFUND_REJECTED, "Refund", "Admin", "", "", 0, 0));
			account.removeRefundRequest(refundIndx);
		}
		else if(answer.toLowerCase().equals("accept")&&transactionIndx!=-1){
			account.addTransaction(new Transaction(Transaction.TYPE.REFUND_ACCEPTED, "Refund", "Admin", "", "", -account.getTransaction(transactionIndx).getAmount(), 0));
			account.setWalletBalance(account.getWalletBalance()+account.getTransaction(transactionIndx).getAmount());
			account.removeRefundRequest(refundIndx);
		}
		else
			return -3;

		return 0;
	}
	
	private boolean checkServiceProviderNames(String serviceName,String serviceProviderName){
		LinkedList <ServiceProvider> temp = getService(serviceName).getServiceProviders();
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).getName().toLowerCase().equals(serviceProviderName.toLowerCase())){return true;}
		}
		return false;
	}
	
}
