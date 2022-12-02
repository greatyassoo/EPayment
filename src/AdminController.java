import java.util.*;
public class AdminController extends ServicesController {
	private LinkedList <Account>accounts;

	AdminController(LinkedList<Service> services , LinkedList<Account> accounts){
		this.services = services ;
		this.accounts = accounts ;
	}
	
	public boolean addServiceProvider(String serviceName,ServiceProvider serviceProvider) {
		try {
		int index = super.getServiceIndex(serviceName);
		this.services.get(index).addServiceProvider(serviceProvider);
		return true;
		}
		catch (Exception e) {return false;}
	}
	
	public boolean setInitialDiscount(double n) {
		if(n<0) return false;
		try {
		for(int i=0;i<services.size();i++) 
			super.services.get(i).setInitialDiscount(n);
		return true;
		}
		catch (Exception e){return false;}
	}

	public boolean setServiceDiscount(String serviceName,double n) {
		try {
			int index = getServiceIndex(serviceName);
			services.get(index).setServiceDiscount(n);
			return true;
		}
		catch (Exception e) {return false;}
	}
	
	public LinkedList<String> getAllAccounts() {
		LinkedList<String> tAccounts = new LinkedList<String>();
		for(int i=0 ; i<accounts.size() ; i++){
			tAccounts.addLast(accounts.get(i).getAllInfo());
		}
		return tAccounts;
	}

	public LinkedList<String> getAllAccountTransactions(int accountIndex){
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

	public String getServiceName(int index){
		return services.get(index).getName();
	}
}
