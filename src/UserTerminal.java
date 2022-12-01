import java.util.LinkedList;
import java.util.Scanner;

public class UserTerminal {
    private UserController controller ;
    private Account account;
    private String currentService,currentServiceProvider;

    UserTerminal(LinkedList<Service> services , Account account){
        this.controller = new UserController(services, account);
        this.account=account;
    }

    public void showOptions(){
        do{
            Scanner scanner = new Scanner(System.in);
            System.out.print("===================\nName: "+account.getUserName()+"\nWallet balance: "+account.getWalletBalance()+"\n");
            System.out.print("===================\n1-Search For service.\n2-List all services.\n3-Fund account.\n4-LogOut.\nChoice: ");
    
            String choice = scanner.nextLine();
    
            System.out.print("===================\n");
            switch(choice){
                case "1" : String serviceName;
                           try {
                            serviceName = searchService();
                            showServiceProviders(serviceName);
                            } 
                           catch (ServiceNameException ex) {System.out.print(ex+" is an invalid Service name name.\n");break;}
                           catch (ServiceProviderNameException ex){System.out.print(ex+" is an invalid service provider name.\n");}

                           
                           break;
    
                case "2" : try {showServiceProviders(showServicesNames());} 
                           catch (ServiceNameException ex) {System.out.print(ex+" is an invalid Service name name.\n");break;}
                           catch (ServiceProviderNameException ex){System.out.print(ex+" is an invalid service provider name.\n");}
                           

                           break;
    
                case "3" : fundAccount();
                           break;

                case "4" : return;

                default : System.out.print("Invalid Choice.\n");
            }
        }while(true);
    }

    public String searchService() throws ServiceNameException{
        System.out.print("Enter Service name: ");
        currentService = new Scanner(System.in).nextLine();

        LinkedList<String> servicesNames = controller.getServices(currentService);
        for(int i=0 ; i<servicesNames.size() ; i++)
            System.out.print((i+1)+"-"+servicesNames.get(i)+".\n");

        System.out.print("Enter Service name: ");
        currentService = new Scanner(System.in).nextLine();

        if(!servicesNames.contains(currentService)){
            throw new ServiceNameException(currentService);
        }
        return currentService;
    }

    public String showServicesNames() throws ServiceNameException{
        LinkedList<String> servicesNames = controller.getServicesNames();
        for(int i=0 ; i<servicesNames.size() ; i++)
            System.out.print((i+1)+"-"+servicesNames.get(i)+".\n");

        System.out.print("Enter selected service name: ");
        currentService = new Scanner(System.in).nextLine();
        
        if(!servicesNames.contains(currentService))
            throw new ServiceNameException(currentService);    
        return currentService;
    }

    public void showServiceProviders(String service) throws ServiceProviderNameException{
        LinkedList<String> serviceProviders = controller.getServiceProviders(service);
        for(int i=0 ; i<serviceProviders.size() ; i++){
            System.out.print((i+1)+"-"+serviceProviders.get(i)+".\n");
        }
        System.out.print("===================\nEnter Service Provider name: ");
        currentServiceProvider = new Scanner(System.in).nextLine();
        if(!serviceProviders.contains(currentServiceProvider))
            throw new ServiceProviderNameException(currentServiceProvider);
        
    }

    public void fundAccount(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter creditCard number: ");
        String CCN = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String PIN = scanner.nextLine();
        System.out.print("Enter ammount: ");
        double ammount = scanner.nextDouble();
        
        if(controller.fundAccount(CCN, PIN, ammount))
            System.out.print("Successfully Recharged "+ammount+" to the wallet.\n");
        else{
            System.out.print("Recharge unsuccessful.\n");
        }
    }

}
