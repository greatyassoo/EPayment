import java.util.*;

//There is only 1 admin account in the system
//user name : admin
//password : admin

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
		System.out.print("Enter service provider name: ");
		String serviceProvider = Main.scanner.nextLine();

		LinkedList<String> services = controller.getServicesNames("");
		printStringList(services);

		System.out.print("Enter service type number: ");
		int serviceIndex = 0;
		try{serviceIndex = Integer.parseInt(Main.scanner.nextLine());}
		catch(Exception e){};
		serviceIndex--;

		try{controller.addServiceProvider(serviceIndex, serviceProvider);}
		catch(Exception e){System.out.print("Error.\n");}
	};
	
	public void manageRefunds() {
		LinkedList<LinkedList<String>> refunds = controller.getRefundRequests();
		if(refunds.size()==0){
			System.out.print("No available refund requests.\n");
			return;
		}

		System.out.print("Refund requests:-\n===================\n");
		for(int i=0 ; i<refunds.size() ; i++){
			System.out.print((i+1)+"-");
			for(int j=0 ; j <refunds.get(i).size() ; j++)
				System.out.print(refunds.get(i).get(j)+" ");
			System.out.print("\n");
		}
		int choice=0;

		do{
			System.out.print("Choice: ");
			try{choice = Integer.parseInt(Main.scanner.nextLine());}
			catch(Exception e){System.out.print("Invalid input.\n");};
			choice--;
			if(choice >= refunds.size() || choice<0) {System.out.print("Invalid, please choose from 1 to"+(refunds.size()+1)+".\n");}
		}while(choice >= refunds.size() || choice<0);

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
	}

	public void getAllAccountTransactions() {
		System.out.print("Accounts:-\n");
		if(controller.getAllAccounts().size()==0){
			System.out.print("No accounts regestered in the system yet.\n");
			return;
		}
        printStringList(controller.getAllAccounts());
		System.out.print("Enter user number: ");
		int accountIndex = 0;
		try{accountIndex = Integer.parseInt(Main.scanner.nextLine());}
		catch(Exception e){};
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
		
		int serviceNumber = 0;
		double discountAmount = 0;
		
		try{
			System.out.print("Enter service number: ");
			serviceNumber = Integer.parseInt(Main.scanner.nextLine());
			System.out.print("Enter discount amount: ");
			discountAmount = Double.parseDouble(Main.scanner.nextLine());
		}catch(Exception e){};
		
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
}
