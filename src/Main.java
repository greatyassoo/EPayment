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
    private static AccountsFetcher accountsFetcher;
    private static DiscountController discountController;

    public static void main(String[] args) {
        services = new LinkedList<Service>();
        accounts = new LinkedList<Account>();
        accountsFetcher = new AccountsFetcher(accounts);
        discountController = new DiscountController();

        accounts.addLast(new Account("test", "test"));
        initializeServices();

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
        UserTerminal terminal = new UserTerminal(services,account,discountController);
        terminal.showOptions();
    }
    
    private static void Admin(){
        AdminTerminal terminal = new AdminTerminal(services,accounts,discountController);
        terminal.showOptions();
    }

    private static void initializeServices() {
        LinkedList<String> sp1 = new LinkedList<String>();
        sp1.addLast(("Vodafone"));
        sp1.addLast(("Etisalat"));
        sp1.addLast(("Orange"));
        sp1.addLast(("WE"));
        Service s1 = new Service("Mobile Recharge", sp1);

        LinkedList<String> sp2 = new LinkedList<String>();
        sp2.addLast(("Vodafone"));
        sp2.addLast(("Etisalat"));
        sp2.addLast(("Orange"));
        sp2.addLast(("WE"));
        Service s2 = new Service("Internet payment", sp2);

        LinkedList<String> sp3 = new LinkedList<String>();
        sp3.addLast("Monthly receipt");
        sp3.addLast("Quarter receipt");
        Service s3 = new Service("Landline", sp3);

        LinkedList<String> sp4 = new LinkedList<String>();
        sp4.addLast("Cancer Hospital");
        sp4.addLast("Schools");
        sp4.addLast("NGOs (Non profitable organizations)");
        Service s4 = new Service("Donations", sp3);
        
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