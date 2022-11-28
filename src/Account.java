import java.util.LinkedList;

public class Account {
	protected String name="";
	protected String email="";
	protected String phoneNumber="";
	protected String password="";
	protected String balance="";
	protected LinkedList<Transaction> transactions;
	
	public void setName(String name){this.name = name;}
	public void setEmail(String email) {this.email = email;}
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
	public void setPassword(String password){this.password = password;}
	public void setBalance(String balance) {this.balance = balance;}
	public void addTransactions(Transaction transactions) {this.transactions.addLast(transactions);}

	public String getName() {return this.name;}
	public String getEmail() {return this.email;}
	public String getPhoneNumber() {return this.phoneNumber;}
	public String getPassword() {return this.password;}	
	public String getBalance() {return this.balance;}
	public LinkedList<Transaction> getTransactions() {return this.transactions;}
}
