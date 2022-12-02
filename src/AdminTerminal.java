import java.util.*;
public class AdminTerminal {
	
	private AdminController aController;
	
	public void showOptions(){
        do{
            Scanner scanner = new Scanner(System.in);
            System.out.print("===================\nName: "+"Admin"+"\n");
            System.out.print("(===================\n1-Add new service provider.\n2-Set initial discount.\n3-Set service discount.\n4-List all users.\n5-List all user transactions.\n6-Accept refund.\n7-Reject refund.\n8-Logout.\nChoice:");
    
            String choice = scanner.nextLine();
            scanner.close();
    
            System.out.print("\n===================\n");
            switch(choice){
            case "1":
            	addServiceProvider();
            	break;
            case "2":
            	setInitialDiscount();
            	break;
            case "3":
            	setServiceDiscount();
            	break;
            case "4":
            	aController.listAllUsers();
            	break;
            case "5":
            	aController.listAllUserTransactions();
            	break;
            case "6":
            	break;
            case "7":
            	break;
            case "8":
            	return;
            default:
            	System.out.println("Invalid Choice. \n");
            }
        }while(true);
    }
	
	public boolean addServiceProvider()
	{
		String serviceName;
		ServiceProvider tmp = new ServiceProvider();
		Form tmpForm = new Form();
		String providerName;
		int nTextBoxes;
		int nDropDowns;
		
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Enter service name: ");
		serviceName = scanner.nextLine();
		System.out.println("Enter service provider name: ");
		providerName = scanner.nextLine();
		tmp.name=providerName;
		System.out.println("Enter amount of textboxes: ");
		nTextBoxes = scanner.nextInt();
		System.out.println("Enter amount of dropdowns: ");
		nDropDowns = scanner.nextInt();
		
		while(tmpForm.getTSize()<nTextBoxes)
		{
			String textBoxName;
			System.out.println("Enter name of text box: ");
			textBoxName = scanner.nextLine();
			tmpForm.addTextBox(textBoxName);
		}
		while(tmpForm.getDSize()<nDropDowns)
		{
			String dropDownName;
			System.out.println("Enter name of drop down: ");
			dropDownName = scanner.nextLine();
			tmpForm.addDropDown(dropDownName);
		}
		scanner.close();
		return aController.addServiceProvider(serviceName,tmp);
	}
	
	public boolean setInitialDiscount(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter discount amount: ");
		int discountAmount = sc.nextInt();
		sc.close();
		return aController.setInitialDiscount(discountAmount);
	}
	public boolean setServiceDiscount(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter discount amount: ");
		int discountAmount = sc.nextInt();
		System.out.println("Enter service name: ");
		String serviceName = sc.nextLine();
		sc.close();
		return aController.setServiceDiscount(discountAmount,serviceName);
	}
	
	

}
