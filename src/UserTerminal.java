import java.util.LinkedList;
import java.util.Scanner;

public class UserTerminal {
    private UserController controller ;
    private Account account;
    public static String currentService="";
    public static ServiceProvider currentServiceProvider = null;
    private int currentServiceIndex,currentServiceProviderIndex;

    UserTerminal(ServicesDB servicesDB , Account account , DiscountController discountController){
        this.controller = new UserController(servicesDB, account, discountController);
        this.account=account;
    }

    public void showOptions(){
        do{
            System.out.print("===================\nName: "+account.getUserName()+"\nWallet balance: "+account.getWalletBalance()+"\n");
            System.out.print("===================\n1-Search For service.\n2-List all services.\n3-Fund account.\n4-Make refund request.\n5-Show discounts\n6-LogOut.\nChoice: ");
    
            String choice = Main.scanner.nextLine();
    
            String serviceName="";
            System.out.print("===================\n");
            switch(choice){
                           
                case "1" : System.out.print("Enter service name: ");
                           serviceName = new Scanner(System.in).nextLine();
    
                case "2" : try {showServicesNames(serviceName);showServiceProvidersNames();} 
                           catch (ServiceNameException ex) {System.out.print(ex);break;}
                           catch (ServiceProviderNameException ex){System.out.print(ex);break;}
                           break;
                           
                case "3" : fundAccount();
                           break;

                case "4" : makeRefundRequest();
                           break;

                case "5" : showDiscounts();
                           break;

                case "6" : return;

                default : System.out.print("Invalid Choice.\n");
            }
        }while(true);
    }

    //Takes service name and searches for it if the name is empty string it 
    //returns all available services
    private void showServicesNames(String name) throws ServiceNameException{
        LinkedList<String> servicesNames = controller.getServicesNames(name);

        if(servicesNames.size()==0){
            currentService = "";
            System.out.print("No matches found.\n");
            return;
        }
        printStringList(servicesNames);
        
        System.out.print("Enter Service number: ");
        try{currentServiceIndex = Integer.parseInt(Main.scanner.nextLine());}
        catch(Exception e){};
        currentServiceIndex--;
        
        if(servicesNames.size()<currentServiceIndex)
            throw new ServiceNameException(Integer.toString(currentServiceIndex));
        
        currentService = servicesNames.get(currentServiceIndex);
    }
 
    public void showServiceProvidersNames() throws ServiceProviderNameException,ServiceNameException{
        if(currentService=="")
            return;
        LinkedList<ServiceProvider> serviceProviders = controller.getServiceProviders(currentService);

        for(int i=0 ; i< serviceProviders.size() ; i++)
            System.out.print((i+1)+"-"+serviceProviders.get(i).getName()+".\n");

        System.out.print("===================\nEnter Service Provider: ");
        
        try{currentServiceProviderIndex = Integer.parseInt(Main.scanner.nextLine());}
        catch(Exception e){};
        currentServiceProviderIndex--;
        
        if(serviceProviders.size()<currentServiceProviderIndex)
            throw new ServiceProviderNameException(Integer.toString(currentServiceProviderIndex));
        
        currentServiceProvider = serviceProviders.get(currentServiceProviderIndex);

        showForm();
    }

    public void showForm(){controller.getService(currentService).getForm().displayForm(currentService, currentServiceProvider, controller.discountController.getDiscount(Service.Names.values()[currentServiceIndex]));}

    public void fundAccount(){
        System.out.print("Payment Done Using Credit Card.\nCardNumber(16 digit only): ");;
        String CCN = Main.scanner.nextLine();
        System.out.print("PIN(4 digits only): ");
        String PIN = Main.scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(Main.scanner.nextLine());
        
        if(controller.fundAccount(CCN, PIN, amount))
            System.out.print("Successfully Recharged "+amount+" to the wallet.\n");
        else
            System.out.print("Recharge unsuccessful.\n");
    }

    public void makeRefundRequest(){
        System.out.print("Refund Requests:-\n");
        printStringList(controller.getRefundRequests());

        System.out.print("Transactions:-\n");
        LinkedList<String> transactions = controller.getTransactions();  //fix this to make it list transactions
        printStringList(transactions);

        System.out.print("Enter Transaction number: ");
        int transactionIndex = 0;
        try{ transactionIndex = Integer.parseInt(Main.scanner.nextLine());}
        catch(Exception e){};
        transactionIndex--;

        if(transactions.size()<transactionIndex || transactionIndex<0){
            System.out.print("Transaction not available.\n");
            return;
        }
        
        boolean isAdded = controller.addRefundRequest(transactionIndex);
        if(!isAdded)
            System.out.print("Transaction number " + (transactionIndex+1) +" already in refund requests.\n");
        else
            System.out.print("Transaction number "+ (transactionIndex+1) +" successfully added to refund requests.\n");
    }

    public void showDiscounts(){
        System.out.print("OverAllDiscount: "+DiscountController.getOverAllDiscount()+"\nServiceDiscounts:-\n");
        for(int i=0 ; i<Service.Names.values().length ; i++)
            System.out.print(Service.Names.values()[i]+": "+controller.discountController.getServiceDiscount(Service.Names.values()[i])+"\n");
    }

    private void printStringList(LinkedList<String> List){
        for(int i=0 ; i< List.size() ; i++)
            System.out.print((i+1)+"-"+List.get(i)+".\n");
    }

}
