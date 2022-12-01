import java.util.LinkedList;

public class UserController extends ServicesController{

    private Account account;

    UserController(LinkedList<Service> services , Account account){
        this.services=services;
        this.account=account;
    }

    public boolean fundAccount(String CCN , String PIN , double ammount){
        account.setWalletBalance(account.getWalletBalance()+ammount);
        return true;
    }

    public LinkedList<String> getServicesNames(){
        LinkedList<String> servicesNames = new LinkedList<String>();
        for(int i=0 ; i<services.size() ; i++){
            servicesNames.addLast(services.get(i).getName());
        }
        return servicesNames;
    }

    public Service getService(String serviceName){
        Service temp=null;
        for(int i=0 ; i<services.size() ; i++){
            if(services.get(i).getName()==serviceName)
                temp=services.get(i);
        }
        return temp;
    }

    // public LinkedList<String> getServiceProviders(String service){
        
    // }
}
