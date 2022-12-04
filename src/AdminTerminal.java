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
    
            String choice = SingleScanner.getInstance().next();
    
            System.out.print("===================\n");
            switch(choice){
            case "1":
            	//addServiceProvider();
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
	
	// public boolean addServiceProvider(){
	// 	String serviceName;
	// 	ServiceProvider tmp = new ServiceProvider();
	// 	Form tmpForm = new Form();
	// 	String providerName;
	// 	int nTextBoxes;
	// 	int nDropDowns;
		
	// 	Scanner scanner = new Scanner(System.in); 
	// 	System.out.println("Enter service name: ");
	// 	serviceName = scanner.nextLine();
	// 	System.out.println("Enter service provider name: ");
	// 	providerName = scanner.nextLine();
	// 	tmp.name=providerName;
	// 	System.out.println("Enter amount of textboxes: ");
	// 	nTextBoxes = scanner.nextInt();
	// 	System.out.println("Enter amount of dropdowns: ");
	// 	nDropDowns = scanner.nextInt();
		
	// 	while(tmpForm.getTSize()<nTextBoxes)
	// 	{
	// 		String textBoxName;
	// 		System.out.println("Enter name of text box: ");
	// 		textBoxName = scanner.nextLine();
	// 		tmpForm.addTextBox(textBoxName);
	// 	}
	// 	while(tmpForm.getDSize()<nDropDowns)
	// 	{
	// 		String dropDownName;
	// 		System.out.println("Enter name of drop down: ");
	// 		dropDownName = scanner.nextLine();
	// 		tmpForm.addDropDown(dropDownName);
	// 	}
	// 	scanner.close();
	// 	return controller.addServiceProvider(serviceName,tmp);
	// }
	
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
				choice = SingleScanner.getInstance().nextInt();
				choice--;
				if(choice > refunds.size() || choice<0) System.out.print("Invalid, please choose from 1 to"+(refunds.size()+1)+".\n");
			}while(choice > refunds.size() || choice<0);

			System.out.print("===================\nPicked: ");
			for(int i=0 ; i<refunds.get(choice).size() ; i++)
				System.out.print(refunds.get(i)+" ");

			System.out.print("\nType Accept/Reject/Cancel: ");
			String answer = SingleScanner.getInstance().nextLine();

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
		int accountIndex = SingleScanner.getInstance().nextInt();
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
		double discountAmount = SingleScanner.getInstance().nextDouble();
		
		return DiscountController.setOverAllDiscount(discountAmount);
	}

	public boolean setServiceDiscount(){
        for(int i=0 ; i<Service.Names.values().length ; i++)
            System.out.print((i+1)+"-"+Service.Names.values()[i]+": "+controller.discountController.getServiceDiscount(Service.Names.values()[i])+"\n");
		
		System.out.print("Enter service number: ");
		int serviceNumber = SingleScanner.getInstance().nextInt();
		System.out.print("Enter discount amount: ");
		double discountAmount = SingleScanner.getInstance().nextDouble();
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
