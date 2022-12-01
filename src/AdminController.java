import java.util.*;
public class AdminController {
	
	private Service service;
	private LinkedList accounts;
	
	public boolean addServiceProvider(ServiceProvider serviceProvider) {
		
		service.addServiceProvider(serviceProvider);
		
		return true;
	}
	
	public void listAllUsers()
	{
		System.out.println(accounts);
	}

}
