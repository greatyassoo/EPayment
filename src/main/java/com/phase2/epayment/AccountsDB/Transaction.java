package com.phase2.epayment.AccountsDB;


public class Transaction {
    private static int tCounter = 0;
    public static enum TYPE {PAYMENT, REFUND_ACCEPTED , REFUND_REJECTED, TOP_UP}

    private String service,serviceProvider,paymentMethod,phoneNumber; 
    private double amount, discount;
    private TYPE type;
    private int transactionID;

    public Transaction(TYPE type, String service, String serviceProvider, String paymentMethod, String phoneNumber, double amount, double discount) {
        this.type = type;
        this.service = service; 
        this.serviceProvider = serviceProvider;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.discount = discount;
        this.phoneNumber=phoneNumber;
        this.transactionID = tCounter++;
    }
    
	//setters
    public void setService(String service) {this.service = service;}
    public void setServiceProvider(String serviceProvider) {this.serviceProvider = serviceProvider;}
    public void setPaymentMethod(String paymentMethod) {this.paymentMethod = paymentMethod;}
    public void setAmount(double amount) {this.amount = amount;}
    public void setDiscount(double discount) {this.discount = discount;}
    public void setType(TYPE type) {this.type = type;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}
    
    
    //getters
    public TYPE getType() {return this.type;}
    public String getService() {return this.service;}
    public String getServiceProvider() {return this.serviceProvider;}
    public String getPaymentMethod() {return this.paymentMethod;}
    public double getAmount() {return this.amount;}
    public double getDiscount() {return this.discount;}
    public String getPhoneNumber() {return this.phoneNumber;}
    public int getTransactionID() {return this.transactionID;}
    public String getAllInfo() {return type+":"+transactionID+":"+service+":"+serviceProvider+":"+amount+":"+paymentMethod+":"+discount;}
}