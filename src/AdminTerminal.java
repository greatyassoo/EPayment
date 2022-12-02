import java.util.*;
public class AdminTerminal {
	
	private AdminController controller;

    AdminTerminal(LinkedList<Service> services,LinkedList<Account> accounts){
        controller = new AdminController(services, accounts);
    }
	
	public void showOptions(){
        do{
            System.out.print("===================\nName: "+"Admin"+"\n");
            System.out.print("===================\n1-Add new service provider.\n2-Set initial discount.\n3-Set service discount.\n4-List all accounts.\n5-List user transactions.\n6-Manage refunds.\n7-Logout.\nChoice:");
    
            String choice = SingleScanner.getInstance().nextLine();
    
            System.out.print("===================\n");
            switch(choice){
            case "1":
            	//addServiceProvider();
            	break;
            case "2":
				if(!setInitialDiscount()) System.out.print("Error, try again.\n");
            	break;
            case "3":
            	if(!setServiceDiscount()) System.out.print("Error, try again.\n");
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
		
	}

	private void getAllAccountTransactions() {
		System.out.print("Accounts:-\n");
        printStringList(controller.getAllAccounts());
		int accountIndex = SingleScanner.getInstance().nextInt();
		accountIndex--;

		if(controller.getAllAccountTransactions(accountIndex)==null){
			System.out.print("Error out of range index.\n");
		}
		
	}

	public boolean setInitialDiscount(){
		printStringList(controller.getServicesDiscounts());
		System.out.println("Enter discount amount: ");
		double discountAmount = SingleScanner.getInstance().nextDouble();
		
		return controller.setInitialDiscount(discountAmount);
	}

	public boolean setServiceDiscount(){
		LinkedList<String> servicesDiscounts = controller.getServicesDiscounts();
		printStringList(servicesDiscounts);
		
		System.out.print("Enter service number: ");
		int serviceNumber = SingleScanner.getInstance().nextInt();
		System.out.print("Enter discount amount: ");
		double discountAmount = SingleScanner.getInstance().nextDouble();
		serviceNumber--;

		if(servicesDiscounts.size()<serviceNumber)
			return false;
		
		String serviceName = controller.getServiceName(serviceNumber);
		if(controller.setServiceDiscount(serviceName,discountAmount))
			System.out.print("Changed "+serviceName+" discount to "+discountAmount+".\n");

		return true; 
	}

	private void printStringList(LinkedList<String> List){
        for(int i=0 ; i< List.size() ; i++)
            System.out.print((i+1)+"-"+List.get(i)+".\n");
    }

}
