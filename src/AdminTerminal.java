import java.util.*;
public class AdminTerminal {
	
	private AdminController controller;

    AdminTerminal(LinkedList<Service> services,LinkedList<Account> accounts,DiscountController discountController){
        controller = new AdminController(services, accounts, discountController);
    }
	
	public void showOptions(){
        do{
            System.out.print("===================\nName: "+"Admin"+"\n");
            System.out.print("===================\n1-Add new service provider.\n2-Set overall discount.\n3-Set service discount.\n4-List all accounts.\n5-List user transactions.\n6-Manage refunds.\n7-Logout.\nChoice:");
    
            String choice = Main.scanner.nextLine();
    
            System.out.print("===================\n");
            switch(choice){
            case "1":
            	addServiceProvider();
            	break;
            case "2":
				if(!setOverAllDiscount()) System.out.print("Error, enter a positive number and try again.\n");
				else System.out.print("Done!\n");
            	break;
            case "3":
            	if(!setServiceDiscount()) System.out.print("Error, try again.\n");
				else System.out.print("Done!\n");
            	break;
            case "4":
				System.out.print("Accounts:-\n");
            	printStringList(controller.getAllAccounts());
            	break;
            case "5":
				getAllAccountTransactions();
            	break;
            case "6":
				manageRefunds();
            	break;
            case "7":
            	return;
            default:
            	System.out.println("Invalid Choice. \n");
            }
        }while(true);
    }
	
	public void addServiceProvider(){
		int serviceIndex;
		System.out.print("Enter service provider name: ");
		String spName = Main.scanner.nextLine();
		LinkedList <String> serviceNames = controller.getServicesNames("");
		printStringList(serviceNames);
		System.out.print("Enter service type(1-4):  ");
		String sType = Main.scanner.nextLine();
		switch(sType)
		{
			case "1":
				sType = "Mobile Recharge Service";
				serviceIndex = controller.getServiceIndex(sType);
				if(checkServiceProviderName(spName, serviceIndex))
				{
					controller.addServiceProvider(serviceIndex,spName);
				}
				break;
			case "2":
				sType = "Internet Payment Service";
				serviceIndex = controller.getServiceIndex(sType);
				if(checkServiceProviderName(spName, serviceIndex))
				{
					controller.addServiceProvider(serviceIndex,spName);
				}
				break;
			case "3":
				sType = "Landline";
				serviceIndex = controller.getServiceIndex(sType);
				if(checkServiceProviderName(spName, serviceIndex))
				{
					controller.addServiceProvider(serviceIndex,spName);
				}
				break;
			case "4":
				sType = "Donations";
				serviceIndex = controller.getServiceIndex(sType);
				if(checkServiceProviderName(spName, serviceIndex))
				{
					controller.addServiceProvider(serviceIndex,spName);
				}
				break;
		}
	};
	
	private void manageRefunds() {
		LinkedList<LinkedList<String>> refunds = controller.getRefundRequests();
		do{
			System.out.print("Refund requests:-\n===================\n");
			for(int i=0 ; i<refunds.size() ; i++){
				System.out.print((i+1)+"-");
				for(int j=0 ; j <refunds.get(i).size() ; j++)
					System.out.print(refunds.get(i).get(j)+" ");
				System.out.print("\n");
			}
			int choice;

			do{
				System.out.print("Choice: ");
				choice = Integer.parseInt(Main.scanner.nextLine());
				choice--;
				if(choice > refunds.size() || choice<0) {System.out.print("Invalid, please choose from 1 to"+(refunds.size()+1)+".\n");}
			}while(choice > refunds.size() || choice<0);

			System.out.println("===================\nPicked: ");
			for(int i=0 ; i<refunds.get(choice).size() ; i++)
				{System.out.print(refunds.get(choice).get(i)+" ");}

			System.out.print("\nType Accept/Reject/Cancel: ");
			String answer = Main.scanner.nextLine();

			int result = controller.processRefundRequest(refunds.get(choice),answer);
			
			switch(result){
				case 0 :
					System.out.print(answer+"ed transaction "+(choice+1)+" successfully.\n");
					return;
				case -1 :
					System.out.print(answer+" is invalid.\n");
					break;
				case -2 :
					System.out.print("Error "+refunds.get(choice).get(0)+" couldn't be found !\n");
					break;
				case -3 :
					System.out.print("Error with transaction/refund request.\n");
					break;
				case -4 :
					System.out.print("Cancelling...\n");
					return;	
			}
		}while(true);
	}

	private void getAllAccountTransactions() {
		System.out.print("Accounts:-\n");
        printStringList(controller.getAllAccounts());
		System.out.print("Enter user number: ");
		int accountIndex = Integer.parseInt(Main.scanner.nextLine());
		accountIndex--;

		LinkedList<String> accountTransactions = controller.getAllAccountTransactions(accountIndex) ;
		if(accountTransactions==null){
			System.out.print("Error out of range index.\n");
			return;
		}
		else if(accountTransactions.size()==0){
			System.out.print("This user has no Transactions.\n");
			return;
		}
		printStringList(accountTransactions);
	}

	public boolean setOverAllDiscount(){
		System.out.print("Overall Discount: "+DiscountController.getOverAllDiscount()+"\n");
		System.out.print("Enter discount amount: ");
		double discountAmount = Double.parseDouble(Main.scanner.nextLine());
		
		return DiscountController.setOverAllDiscount(discountAmount);
	}

	public boolean setServiceDiscount(){
        for(int i=0 ; i<Service.Names.values().length ; i++)
            System.out.print((i+1)+"-"+Service.Names.values()[i]+": "+controller.discountController.getServiceDiscount(Service.Names.values()[i])+"\n");
		
		System.out.print("Enter service number: ");
		int serviceNumber = Integer.parseInt(Main.scanner.nextLine());
		System.out.print("Enter discount amount: ");
		double discountAmount = Double.parseDouble(Main.scanner.nextLine());
		serviceNumber--;

		if(Service.Names.values().length < serviceNumber || serviceNumber < 0)
			return false;

		controller.discountController.setServiceDiscount(Service.Names.values()[serviceNumber],discountAmount);
		System.out.print("Changed "+Service.Names.values()[serviceNumber]+" discount to "+discountAmount+".\n");

		return true; 
	}

	private void printStringList(LinkedList<String> List){
        for(int i=0 ; i< List.size() ; i++)
            System.out.print((i+1)+"-"+List.get(i)+".\n");
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
