package com.phase2.epayment.AccountsDB;
import java.util.LinkedList;

public class Account {
	private String name;
	private String email;
	private String phoneNumber;
	private String password;
	private Double walletBalance = 0.0;
	private LinkedList<Transaction> transactions;
	private LinkedList<Integer> refundRequests;

	public Account(){};

	Account(String name,String password,String email, String phoneNumber){
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.transactions = new LinkedList<Transaction>();
		this.refundRequests = new LinkedList<Integer>();
	}
	
	// setters
	public void setUserName(String name){this.name = name;}
	public void setEmail(String email) {this.email = email;}
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
	public void setPassword(String password){this.password = password;}
	public void setWalletBalance(Double walletBalance) {this.walletBalance = walletBalance;}
	public void addTransaction(Transaction transactions) {this.transactions.addLast(transactions);}
	public void addRefundRequest(int transactionID){this.refundRequests.addLast(transactionID);};

	// getters
	public String getUserName() {return this.name;}
	public String getUserEmail() {return this.email;}
	public String getPhoneNumber() {return this.phoneNumber;}
	public String getPassword() {return this.password;}	
	public Double getWalletBalance() {return this.walletBalance;}
	public Transaction getTransaction(int indx) {return transactions.get(indx);}
	public LinkedList<Transaction> getTransactions() {return this.transactions;}
	public LinkedList<Integer> getRefundRequests() {return this.refundRequests;}

	public boolean removeTransaction(int index){
		try{transactions.remove(index);}
		catch(Exception e){return false;}
		return true;
	}
	public boolean removeRefundRequest(int index){
		try{refundRequests.remove(index);}
		catch(Exception e){return false;}
		return true;
	}
}
