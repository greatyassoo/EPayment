
public class Authenticator implements AccountAuthentication{
    private AccountsFetcher accountsFetcher;
    public static final String ADMIN_NAME = "admin", ADMIN_PASSWORD = "admin";

    Authenticator(AccountsFetcher accountsFetcher){
        this.accountsFetcher=accountsFetcher;
    }

    private int authenticateLogin(String userName, String password) { // -1 for error, 0 for admin, 1 for user.
        if (userName.equals(ADMIN_NAME) && password.equals(ADMIN_PASSWORD))
            return 0; // admin

        for(int i = 0; i < accountsFetcher.getSize(); i++){
            if(accountsFetcher.getAccount(i).getUserName().equals(userName) && accountsFetcher.getAccount(i).getPassword().equals(password))
                return 1; // user
        }
        return -1; // error
    }

    private boolean authenticateSignup(Account account){
        for(int i = 0; i < accountsFetcher.getSize(); i++){
            if(account.getUserName().equals(ADMIN_NAME) || accountsFetcher.getAccount(i).getUserName().equals(account.getUserName()))
                return false;
        }
        return true;
    }

    @Override
    public Account login(String userName, String password) {
        int choice = authenticateLogin(userName, password);
        switch(choice){
            case -1 : return null; // return error
            case 0 : return new Account(ADMIN_NAME, ADMIN_PASSWORD); // return admin account
            case 1 : return accountsFetcher.login(userName, password); // return user account
        }
        return null;
    }

    @Override
    public boolean signUp(Account account) {
        if(!authenticateSignup(account))
            return false;
        accountsFetcher.signUp(account);
        return true;
    }

}