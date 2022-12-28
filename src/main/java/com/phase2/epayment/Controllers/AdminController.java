package com.phase2.epayment.Controllers;

import com.phase2.epayment.ServicesDB.*;
import com.phase2.epayment.AccountsDB.*;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/admin-controller") // this means any mapping inside this class starts with "/admincontroller"
public class AdminController extends ServicesController {
	// private LinkedList <Account>accounts;
	private AccountsFetcher accountsFetcher;

	AdminController(ServicesDB servicesDB, AccountsFetcher accountsFetcher){
		this.servicesDB = servicesDB ;
		//this.discountController = discountController;
		this.accountsFetcher = accountsFetcher;
	}
	
	//TODO
	public boolean addServiceProvider(int serviceIndex,String serviceProvider) {
		try {
			if(checkServiceProviderNames(serviceIndex,serviceProvider)){
				System.out.println("Service provider alreadty exists!");
				return false;
			}
			getService(serviceIndex).addServiceProvider(new ServiceProvider(serviceProvider));
			return true;
		}
		catch (Exception e) {return false;}
	}
	

	@GetMapping("all-accounts")
	public LinkedList<String> getAllAccounts() {
		LinkedList<Account> accounts = accountsFetcher.getAccountsList();
		LinkedList<String> tAccounts = new LinkedList<String>();
		for(int i=0 ; i<accounts.size() ; i++){
			tAccounts.addLast(accounts.get(i).getAllInfo());
		}
		return tAccounts;
	}

	@GetMapping("account")
	public Account getAccount(@RequestParam("userName") String userName){
		LinkedList<Account> accounts = accountsFetcher.getAccountsList();
		for(int i=0 ; i<accounts.size() ; i++)
			if(accounts.get(i).getUserName().equals(userName))
				return accounts.get(i);
		throw new IllegalAccessError("Account does not exist.");
	}

	public LinkedList<String> getAllAccountTransactions(int accountIndex){	
		LinkedList<Account> accounts = accountsFetcher.getAccountsList();
		try {Account account = accounts.get(accountIndex);
			LinkedList<String> transactionsString = new LinkedList<String>();
			LinkedList<Transaction> transactions = account.getTransactions();
			for(int i=0 ; i<transactions.size() ; i++){
				transactionsString.addLast(transactions.get(i).getAllInfo());
			}
			return transactionsString;
		} 
		catch (Exception e) {return null;}
	}

	@GetMapping("all-refunds")
	public LinkedList<LinkedList<String>> getRefundRequests(){
		LinkedList<Account> accounts = accountsFetcher.getAccountsList();
		LinkedList<LinkedList<String>> refundRequests = new LinkedList<LinkedList<String>>();
		for(int i=0 ; i<accounts.size() ; i++){
			for(int j=0 ; j<accounts.get(i).getRefundRequests().size() ; j++){
				int indx = accounts.get(i).getRefundRequests().get(j);
				Transaction transaction = accounts.get(i).getTransaction(indx);
				LinkedList<String> temp = new LinkedList<String>();
				temp.addLast(accounts.get(i).getUserName());
				temp.addLast(transaction.getService());
				temp.addLast(Double.toString(transaction.getAmount()));
				refundRequests.addLast(temp);
			}
		}
		return refundRequests;
	}

	public String getServiceName(int index){
		return servicesDB.get(index).getName();
	}

	public int processRefundRequest(LinkedList<String> refund, String answer) {
		if( answer.toLowerCase().equals("cancel"))
			return -4;
		if(!answer.toLowerCase().equals("accept") && !answer.toLowerCase().equals("reject"))
			return -1;

		String userName = refund.get(0);
		String service = refund.get(1);
		double amount = Double.parseDouble(refund.get(refund.size()-1));
		Account account = getAccount(userName);
		
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
	
	private boolean checkServiceProviderNames(int serviceIndex,String name){
		LinkedList <ServiceProvider> temp = getService(serviceIndex).getServiceProviders();
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).getName().toLowerCase().equals(name.toLowerCase())){return true;}
		}
		return false;
	}
}
