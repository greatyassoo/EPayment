import java.util.LinkedList;

public class AccountsFetcher implements AccountAuthentication{
    private LinkedList<Account> accounts;

    AccountsFetcher(LinkedList<Account> accounts){
        this.accounts = accounts;
    }

    public Account login(String uName , String password){ 
        for(int i=0 ; i<accounts.size() ; i++){
            if(accounts.get(i).getUserName().equals(uName) && accounts.get(i).getPassword().equals(password)){
                return accounts.get(i);
            }
        }
        return null;
    }

    public boolean signUp(Account account){
        accounts.add(account);
        return true;
    }

    public Account getAccount(int index) {
        return accounts.get(index);
    }
    public int getSize() {
        try {return accounts.size();}
        catch (Exception e) {return 0;}
    }
}
