import java.util.LinkedList;
import java.util.Scanner;

public class UserTerminal {
    UserController controller ;
    Account account;
    
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
                case "1" : searchService();
                           showServiceProviders();

                           break;
    
                case "2" :showServicesNames();
                          
                          break;
    
                case "3" : fundAccount();
                           break;

                case "4" : return;

                default : System.out.print("Invalid Choice.\n");
            }
        }while(true);
    }

    public void searchService(){
        System.out.print("Enter Service name: ");
        String serviceName = new Scanner(System.in).nextLine();
        LinkedList<Service> found = controller.getServices(serviceName);
        showServicesNames(found);
    }

    public void showServicesNames(){
        LinkedList<String> servicesNames = controller.getServicesNames();
        for(int i=0 ; i<servicesNames.size() ; i++){
            System.out.print((i+1)+"-"+servicesNames.get(i)+".");
        }
    }

    public void showServicesNames(LinkedList<Service> servicesNames){
        for(int i=0 ; i<servicesNames.size() ; i++){
            System.out.print((i+1)+"-"+servicesNames.get(i)+".");
        }
    }

    public void showServiceProviders(Service service){
        LinkedList<ServiceProvider> serviceProviders = service.getServiceProviders();
    }

    public void showServiceProviders(){
        System.out.print("Enter selected service name: ");
        String serviceName = new Scanner(System.in).nextLine();
        controller.getServiceProviders(controller.getService(serviceName));

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
