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

    public LinkedList<String> getTransactions(){
        LinkedList<String> transactionsNames = new LinkedList<String>();
        return transactionsNames;
    }

    public LinkedList<String> getRefundRequests(){
        LinkedList<Integer> refundRequestsIndx = account.getRefundRequests();
        LinkedList<String> refundRequests = new LinkedList<String>();
        Transaction transaction;
        for(int i=0 ; i<refundRequestsIndx.size() ; i++){
            transaction = account.getTransaction(refundRequestsIndx.get(i));
            String transactionString = transaction.getService()+":"+transaction.getServiceProvider()+":"+transaction.getAmount()+":"+transaction.getPaymentMethod()+":"+transaction.getDiscount()+":"+transaction.getData();
            refundRequests.addLast(transactionString);
        }
        return refundRequests;
    }
    public boolean addRefundRequest(int indx){
        if(account.getRefundRequests().contains(indx)){
            return false;
        }
        account.addRefundRequest(indx);
        return true;
    }

    public LinkedList<String> getDiscounts(){
        LinkedList<String> discounts = new LinkedList<String>();
        for(int i=0 ; i< services.size() ; i++)
            discounts.addLast(services.get(i).getName()+" - Discount : "+services.get(i).getServiceDiscount()+"% , Initial : "+services.get(i).getInitialDiscount()+"%");
        return discounts;
    }
}
