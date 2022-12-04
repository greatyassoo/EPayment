import java.util.*;
public class AdminController extends ServicesController {
	private LinkedList <Account>accounts;

	AdminController(LinkedList<Service> services , LinkedList<Account> accounts , DiscountController discountController){
		this.services = services ;
		this.accounts = accounts ;
		this.discountController = discountController;
	}
	
	public boolean addServiceProvider(int serviceIndex,String serviceProvider) {
		try {
		if(getService(serviceIndex).getServiceProviders().contains(serviceProvider))
			return false;
		getService(serviceIndex).addServiceProvider(serviceProvider);
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

	public int processRefundRequest(LinkedList<String> refund, String answer) {
		if( answer.toLowerCase().equals("cancel"))
			return -4;
		if(! answer.toLowerCase().equals("accept") || !answer.toLowerCase().equals("reject"))
			return -1;

		String userName = refund.get(0);
		String service = refund.get(1);
		double amount = Double.parseDouble(refund.get(refund.size()-1));
		Account account = geAccount(userName);
		
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
	public boolean checkServiceProviderName(String name,int index)
	{
		for(int i=0;i<controller.getService(index).getServiceProviders().size();i++)
		{
			if(name.equals(controller.getService(index).getServiceProviders().get(i)))
			{
				return false;
			}
		}
		return true;
	}
}
