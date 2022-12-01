import java.util.*;

public class Main {
    private static LinkedList<Service> services;
    private static LinkedList<Account> accounts;

    public static void main(String[] args) {
        services = new LinkedList<Service>();
        accounts = new LinkedList<Account>();

        AccountsFetcher accountsFetcher = new AccountsFetcher(accounts);
        LoginTerminal terminal = new LoginTerminal(accountsFetcher);
        while(true){            Scanner scanner = new Scanner(System.in);

            System.out.print("===================\n1-Login.\n2-SignUp.\n3-Exit\n===================\nChoice: ");
            String answer = scanner.nextLine();
            
            ////scanner.close();

            if(answer.equals("1")){
                Account tAccount = terminal.logIn();
                if(tAccount==null)
                    System.out.println("Wrong info.");
                else if(tAccount.getUserName().equals(Authenticator.ADMIN_NAME))
                    Admin();
                else
                    User(tAccount);
            }
            else if(answer.equals("2")){
                if(terminal.signUp())
                    System.out.println("Account created successfully !!");
                else
                    System.out.println("Error Account invalid.");
            }
            else if(answer.equals("3")){
                scanner.close();
                return;
            }
            else{
                System.out.println("Please Enter a valid choice.");
            }
        }

    }
    
    private static void User(Account account){
        UserTerminal terminal = new UserTerminal(services,account);
        terminal.showOptions();
    }
    
    private static void Admin(){
        
    }
}
























        // LinkedList<Account> accounts = new LinkedList<Account>();
        // LinkedList<Service> services = new LinkedList<Service>();
        // LinkedList<ServiceProvider> serviceProviders = new LinkedList<ServiceProvider>(Arrays.asList(new ServiceProvider("Vodafone",new Form()),new ServiceProvider("We",new Form()),new ServiceProvider("Etisalat",new Form())));
        // services.addLast(new Service("Mobile",1.1,1.2,serviceProviders));
        // Controller c = new UserController(services,new LinkedList<Account>());
        
        // LinkedList<Service> s= c.getServices();
        // System.out.println(s.getFirst().getName());
        // LinkedList<ServiceProvider> f = c.getServiceProviders(s.getFirst());
        // for(int i=0 ; i<f.size() ; i++){
        //     System.out.println(f.get(i).name);
        // }