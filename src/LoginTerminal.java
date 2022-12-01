import java.util.Scanner;

public class LoginTerminal {
    private Authenticator authenticator;
    private Scanner scanner;

    LoginTerminal(AccountsFetcher accountsFetcher)
    {
        this.authenticator = new Authenticator(accountsFetcher);
    }

    public Account logIn(){
        scanner = new Scanner(System.in);
        String userName,password;

        System.out.print("Enter username");
        userName=scanner.nextLine();
        System.out.print("Enter password");
        password=scanner.nextLine();
        scanner.close();
        return authenticator.login(userName, password);
    }
    public boolean signUp(){
        scanner = new Scanner(System.in);

        String userName,password,email,phoneNumber;
        System.out.print("Enter username");
        userName=scanner.nextLine();
        System.out.print("Enter password");
        password=scanner.nextLine();
        System.out.print("Enter email");
        email=scanner.nextLine();
        System.out.print("Enter phoneNumber");
        phoneNumber=scanner.nextLine();

        Account tAccount = new Account(userName, password, email, phoneNumber);

        scanner.close();
        return authenticator.signUp(tAccount);
    }



    //testing function
    public boolean signUp(Account account) {
        return authenticator.signUp(account);
    }

    public Account logIn(Account account){
        return authenticator.login(account.getUserName(), account.getPassword());
    }

}
