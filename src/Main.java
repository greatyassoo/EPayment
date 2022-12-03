import java.util.*;

public class Main {
    // public static void main(String[] args) {

        
    //     LinkedList<String> serviceproviders = new LinkedList<String>();
    //     serviceproviders.add("vodafone");
    //     serviceproviders.add("Etisalat");
        

    //     LinkedList<Service> services = new LinkedList<Service>();
    //     services.add(new Service("Mobile Recharge", 10,10, serviceproviders));

    //     //Service landline = new Service("Landline", 10, 10,serviceProviders);



    //     UserController controller = new UserController(services, new Account("Youssef", "123"));
    //     controller.pay("Mobile Recharge", "vodafone");
        
    // }    
    
    
    
    
    
    
    
    
    private static LinkedList<Service> services;
    private static LinkedList<Account> accounts;

    public static void main(String[] args) {
        services = new LinkedList<Service>();
        accounts = new LinkedList<Account>();

        accounts.addLast(new Account("test", "test"));

        LinkedList<String> sp1 = new LinkedList<String>();
        sp1.addLast(("Vodafone"));
        sp1.addLast(("Etisalat"));
        sp1.addLast(("Orange"));
        sp1.addLast(("WE"));
        
        Service s1 = new Service("Mobile Recharge", 10, 5, sp1);
        services.addLast(s1);
        
        AccountsFetcher accountsFetcher = new AccountsFetcher(accounts);

        //Admin();

        LoginTerminal terminal = new LoginTerminal(accountsFetcher);
        while(true){            
            System.out.print("===================\n1-Login.\n2-SignUp.\n3-Exit\n===================\nChoice: ");
            String answer = SingleScanner.getInstance().nextLine();

            if(answer.equals("1")){
                Account tAccount = terminal.logIn();
                if(tAccount==null)
                    System.out.println("Wrong info/User doesn't exist.");
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
                SingleScanner.getInstance().close();
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
        AdminTerminal terminal = new AdminTerminal(services,accounts);
        terminal.showOptions();
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