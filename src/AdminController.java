import java.util.*;
public class AdminController extends ServicesController {
	
	
	private LinkedList <Account>accounts;
	
	public boolean addServiceProvider(String serviceName,ServiceProvider serviceProvider) {
		try {
		int index = super.getServiceIndex(serviceName);
		this.services.get(index).addServiceProvider(serviceProvider);
		return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public boolean setInitialDiscount(double n) {
		try {
		for(int i=0;i<services.size();i++) {
			super.services.get(i).setInitialDiscount(n);
		}
		return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public boolean setServiceDiscount(double n,String serviceName) {
		try {
			int index = super.getServiceIndex(serviceName);
			super.services.get(index).setServiceDiscount(n);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public void listAllUsers()
	{
		System.out.println(accounts);
	}
	public void listAllUserTransactions() {
		for(int i=0;i<accounts.size();i++)
		{
			System.out.println(accounts.get(i).getTransactions());
		}
	}

}
