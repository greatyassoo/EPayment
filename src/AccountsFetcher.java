import java.util.LinkedList;

public class AccountsFetcher {
    private LinkedList<Account> accounts;

    AccountsFetcher(){
        accounts = new LinkedList<Account>();
    }

    public Account Login(String uName , String password){ 
        for(int i=0 ; i<accounts.size() ; i++){
            if(accounts.get(i).name==uName && accounts.get(i).password==password){
                return accounts.get(i);
            }
        }
        return null;
    }
    public boolean SignUp(Account account){
        try{
            accounts.add(account);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
