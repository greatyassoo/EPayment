import java.util.*;

public class Main {
    public static void main(String[] args) {
        Account tAccount = new Account("","");

        LinkedList<Service> services = new LinkedList<Service>();
        LinkedList<Account> accounts = new LinkedList<Account>();


        AccountsFetcher accountsFetcher = new AccountsFetcher(accounts);
        ServicesController servicesController = new UserController(services,tAccount);

        LoginTerminal terminal = new LoginTerminal(accountsFetcher);
        Account account = new Account("youssef", "123", "yahoo.com", "123456");
        Account admin = new Account("admin", "admin");
        Account noAccount = new Account("lol", "fuck u");

        if(terminal.signUp(account)){
            System.out.println("first signUp success");
        }
        else{
            System.out.println("Fku");
        }

        Account temp = terminal.logIn(admin);
        if(temp !=null ){
            System.out.println(temp.getUserName());
        }
        else{
            System.out.println("Fku");
        }
        
        







        

        























        // LinkedList<Account> accounts = new LinkedList<Account>();
        // LinkedList<Service> services = new LinkedList<Service>();
        // LinkedList<FormHandler> serviceProviders = new LinkedList<FormHandler>(Arrays.asList(new FormHandler("Vodafone",new Form()),new FormHandler("We",new Form()),new FormHandler("Etisalat",new Form())));
        // services.addLast(new Service("Mobile",1.1,1.2,serviceProviders));
        // Controller c = new UserController(services,new LinkedList<Account>());
        
        // LinkedList<Service> s= c.getServices();
        // System.out.println(s.getFirst().getName());
        // LinkedList<FormHandler> f = c.getServiceProviders(s.getFirst());
        // for(int i=0 ; i<f.size() ; i++){
        //     System.out.println(f.get(i).name);
        // }
    }
}