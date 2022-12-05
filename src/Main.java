import java.util.*;

public class Main {
       
    private static LinkedList<Service> services;
    private static LinkedList<Account> accounts;
    private static AccountsFetcher accountsFetcher;
    private static DiscountController discountController;
    public static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        services = new LinkedList<Service>();
        accounts = new LinkedList<Account>();
        accountsFetcher = new AccountsFetcher(accounts);
        discountController = new DiscountController();

        accounts.addLast(new Account("test", "test"));
        initializeServices();

        LoginTerminal terminal = new LoginTerminal(accountsFetcher);
        while(true){            
            System.out.print("===================\n1-Login.\n2-SignUp.\n3-Exit\n===================\nChoice: ");
            String answer = Main.scanner.nextLine();

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
                Main.scanner.close();
                return;
            }
            else{
                System.out.println("Please Enter a valid choice.");
            }
        }
    }

    private static void User(Account account){
        UserTerminal terminal = new UserTerminal(services,account,discountController);
        terminal.showOptions();
    }
    
    private static void Admin(){
        AdminTerminal terminal = new AdminTerminal(services,accounts,discountController);
        terminal.showOptions();
    }

    private static void initializeServices() {
        LinkedList<ServiceProvider> sp1 = new LinkedList<ServiceProvider>();
        sp1.addLast(new ServiceProvider("Vodafone"));
        sp1.addLast(new ServiceProvider("Etisalat"));
        sp1.addLast(new ServiceProvider("Orange"));
        sp1.addLast(new ServiceProvider("WE"));
        Service s1 = new MobileRechargeServiceFactory().createService(sp1);

        LinkedList<ServiceProvider> sp2 = new LinkedList<ServiceProvider>();
        sp2.addLast(new ServiceProvider("Vodafone"));
        sp2.addLast(new ServiceProvider("Etisalat"));
        sp2.addLast(new ServiceProvider("Orange"));
        sp2.addLast(new ServiceProvider("WE"));
        Service s2 = new InternetServiceFormFactory().createService(sp2);

        LinkedList<ServiceProvider> sp3 = new LinkedList<ServiceProvider>();
        sp3.addLast(new ServiceProvider("Monthly receipt"));
        sp3.addLast(new ServiceProvider("Quarter receipt"));
        Service s3 = new LandlineServiceFormFactory().createService(sp3);

        LinkedList<ServiceProvider> sp4 = new LinkedList<ServiceProvider>();
        sp4.addLast(new ServiceProvider("Cancer Hospital"));
        sp4.addLast(new ServiceProvider("Schools"));
        sp4.addLast(new ServiceProvider("NGOs (Non profitable organizations)"));
        Service s4 = new DonationsServiceFormFactory().createService(sp4);
        
        services.addLast(s1);
        services.addLast(s2);
        services.addLast(s3);
        services.addLast(s4);
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