import java.util.*;

public class Main {
    private static LinkedList<Account> accounts;
    private static AccountsFetcher accountsFetcher;
    private static DiscountController discountController;
    public static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        accounts = new LinkedList<Account>();
        accountsFetcher = new AccountsFetcher(accounts);
        discountController = new DiscountController();

        accounts.addLast(new Account("test", "test"));

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
}