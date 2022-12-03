import java.util.*;
public class AdminController extends ServicesController {
	private LinkedList <Account>accounts;

	AdminController(LinkedList<Service> services , LinkedList<Account> accounts){
		this.services = services ;
		this.accounts = accounts ;
	}
	
	public boolean addServiceProvider(String serviceName,String serviceProvider) {
		try {
		int index = super.getServiceIndex(serviceName);
		this.services.get(index).addServiceProvider(serviceProvider);
		return true;
		}
		catch (Exception e) {return false;}
	}
	
	public boolean addOverAllDiscount(double n) {
		if(n<0) return false;
		try {
		for(int i=0;i<services.size();i++) 
			super.services.get(i).addOverAllDiscount(n);
		return true;
		}
		catch (Exception e){return false;}
	}

	public boolean addServiceDiscount(String serviceName,double n) {
		try {
			int index = getServiceIndex(serviceName);
			services.get(index).addServiceDiscount(n);
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

	private Account geAccount(String userName){
		for(int i=0 ; i<accounts.size() ; i++)
			if(accounts.get(i).getUserName()==userName)
				return accounts.get(i);
		return null;
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

	public LinkedList<LinkedList<String>> getRefundRequests(){
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
		return services.get(index).getName();
	}

	public boolean processRefundRequest(LinkedList<String> refund, String answer) {
		if(answer.toLowerCase()!="accept" || answer.toLowerCase()!="reject")
			return false;

		String userName = refund.get(0);
		String service = refund.get(1);
		double ammount = Double.parseDouble(refund.get(refund.size()-1));
		Account account = geAccount(userName);
		
		if(account==null)
			return false;

		
		

		return true;
	}
}
