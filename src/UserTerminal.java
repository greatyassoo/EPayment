import java.util.LinkedList;
import java.util.Scanner;

public class UserTerminal {
    private UserController controller ;
    private Account account;
    private String currentService="",currentServiceProvider="";

    UserTerminal(LinkedList<Service> services , Account account){
        this.controller = new UserController(services, account);
        this.account=account;
    }

    public void showOptions(){
        do{
            System.out.print("===================\nName: "+account.getUserName()+"\nWallet balance: "+account.getWalletBalance()+"\n");
            System.out.print("===================\n1-Search For service.\n2-List all services.\n3-Fund account.\n4-Make refund request.\n5-Show discounts\n6-LogOut.\nChoice: ");
    
            String choice = SingleScanner.getInstance().nextLine();
    
            String serviceName="";
            System.out.print("===================\n");
            switch(choice){
                case "1" : serviceName = new Scanner(System.in).nextLine();
    
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
        printStringList(servicesNames);
        
        System.out.print("Enter Service number: ");
        int currentServiceIndex = SingleScanner.getInstance().nextInt();
        currentServiceIndex--;
        
        if(servicesNames.size()<currentServiceIndex)
            throw new ServiceNameException(Integer.toString(currentServiceIndex));
        
        currentService = servicesNames.get(currentServiceIndex);
    }
 
    public void showServiceProvidersNames() throws ServiceProviderNameException,ServiceNameException{
        LinkedList<String> serviceProviders = controller.getServiceProviders(currentService);
        printStringList(serviceProviders);
        System.out.print("===================\nEnter Service Provider: ");
        
        int currentServiceProviderIndex = SingleScanner.getInstance().nextInt();
        currentServiceProviderIndex--;
        
        if(serviceProviders.size()<currentServiceProviderIndex)
            throw new ServiceProviderNameException(Integer.toString(currentServiceProviderIndex));
        
        currentServiceProvider = serviceProviders.get(currentServiceProviderIndex);
    }

    public void fundAccount(){
        System.out.print("Enter creditCard number: ");
        String CCN = SingleScanner.getInstance().nextLine();
        System.out.print("Enter PIN: ");
        String PIN = SingleScanner.getInstance().nextLine();
        System.out.print("Enter ammount: ");
        double ammount = SingleScanner.getInstance().nextDouble();
        
        if(controller.fundAccount(CCN, PIN, ammount))
            System.out.print("Successfully Recharged "+ammount+" to the wallet.\n");
        else{
            System.out.print("Recharge unsuccessful.\n");
        }
    }

    public void makeRefundRequest(){
        System.out.print("Refund Requests:-\n");
        printStringList(controller.getRefundRequests());

        LinkedList<String> transactions = controller.getTransactions();

        System.out.print("Enter Transaction number: ");
        int transactionIndex = SingleScanner.getInstance().nextInt();
        transactionIndex--;

        if(transactions.size()<transactionIndex || transactionIndex <0){
            System.out.print("Transaction not available.\n");
            return;
        }
        
        boolean isAdded = controller.addRefundRequest(transactionIndex);
        if(!isAdded)
            System.out.print("Transaction number" + transactionIndex +" already in refund requests.\n");
        else
            System.out.print("Transaction number "+ transactionIndex +" successfully added to refund requests.\n");
    }

    public void showDiscounts(){
        printStringList(controller.getServicesDiscounts());
    }

    private void printStringList(LinkedList<String> List){
        for(int i=0 ; i< List.size() ; i++)
            System.out.print((i+1)+"-"+List.get(i)+".\n");
    }
    

}
