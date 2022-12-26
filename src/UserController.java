import java.util.LinkedList;

    public class UserController extends ServicesController{

    public static Account account;

    UserController(ServicesDB servicesDB , Account a , DiscountController discountController){
        this.servicesDB = servicesDB;
        account = a;
        this.discountController = discountController;
    }

    public boolean fundAccount(String CCN , String PIN , double amount){
        try {
            if(!CCN.matches("-?\\d+(\\.\\d+)?") || CCN.length()!=16 || PIN.length()!=4 || !PIN.matches("-?\\d+(\\.\\d+)?"))
                return false;
            account.setWalletBalance(account.getWalletBalance()+amount);
            account.addTransaction(new Transaction(Transaction.TYPE.TOP_UP, "Recharge", "CreditCard", "CreditCard", "", amount, 0));
            return true;
        } catch (Exception e) {return false;}
    }

    public Service getService(String serviceName){
        Service temp=null;
        for(int i=0 ; i<servicesDB.size() ; i++){
            if(servicesDB.get(i).getName()==serviceName)
                temp=servicesDB.get(i);
        }
        return temp;
    }

    public LinkedList<String> getTransactions(){
        LinkedList<Transaction> transactions = account.getTransactions();
        LinkedList<String> transactionsList = new LinkedList<String>();
        for(int i=0 ; i<transactions.size() ; i++)
            transactionsList.addLast(transactions.get(i).getAllInfo());
        return transactionsList;
    }

    public LinkedList<String> getRefundRequests(){
        LinkedList<Integer> refundRequestsIndx = account.getRefundRequests();
        LinkedList<String> refundRequests = new LinkedList<String>();
        Transaction transaction;
        for(int i=0 ; i<refundRequestsIndx.size() ; i++){
            transaction = account.getTransaction(refundRequestsIndx.get(i));
            String transactionString = transaction.getAllInfo();
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
}
