import java.util.LinkedList;

    public class UserController extends ServicesController{

    public static Account account;

    UserController(LinkedList<Service> services , Account account , DiscountController discountController){
        this.services = services;
        this.account = account;
        this.discountController = discountController;
    }

    public boolean fundAccount(String CCN , String PIN , double ammount){
        try {
            account.setWalletBalance(account.getWalletBalance()+ammount);
            account.addTransactions(new Transaction(Transaction.TYPE.TOP_UP, "Recharge", "CreditCard", "CreditCard", "", ammount, 0));
            return true;
        } catch (Exception e) {return false;}
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
            String transactionString = transaction.getService()+":"+transaction.getServiceProvider()+":"+transaction.getAmount()+":"+transaction.getPaymentMethod()+":"+transaction.getDiscount();
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

    // testing 
    // public void pay(String serviceName, String serviceProviderName){
    //     getService(serviceName).initForm(serviceProviderName);
    // }
}
