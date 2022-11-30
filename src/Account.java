import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class Account {
	protected String name="";
	protected String email="";
	protected String phoneNumber="";
	protected String password="";
	protected String walletBalance="";
	protected LinkedList<Transaction> transactions;
	protected LinkedList<Integer> refundRequests;
	
	public void setName(String name){this.name = name;}
	public void setEmail(String email) {this.email = email;}
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
	public void setPassword(String password){this.password = password;}
	public void setWalletBalance(String walletBalance) {this.walletBalance = walletBalance;}
	public void addTransactions(Transaction transactions) {this.transactions.addLast(transactions);}
	public void addRefundRequest(int index){this.refundRequests.addLast(index);};

	public String getName() {return this.name;}
	public String getEmail() {return this.email;}
	public String getPhoneNumber() {return this.phoneNumber;}
	public String getPassword() {return this.password;}	
	public String getWalletBalance() {return this.walletBalance;}
	public LinkedList<Transaction> getTransactions() {return this.transactions;}
	public LinkedList<Integer> getRefundRequests() {return this.refundRequests;}

	public boolean removeTransaction(int indx){
		try{transactions.remove(indx);}
		catch(Exception e){return false;}
		return true;
	}

	public boolean removeRefundRequest(int indx){
		try{refundRequests.remove(indx);}
		catch(Exception e){return false;}
		return true;
	}
}
