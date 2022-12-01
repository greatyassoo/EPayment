import java.util.LinkedList;


public class Transaction {
    private String service, serviceProvider;
    private LinkedList<String> Data = new LinkedList<String>(); // data is the unknown data that varies between different transactions.
    private double amount;

    Transaction(String service, String serviceProvider, LinkedList<String> data, double amount) {
        this.service = service; 
        this.serviceProvider = serviceProvider;
        this.Data = data;
        this.amount = amount;
    }
       
    
    //setters
    public void setService(String service) {this.service = service;}
    public void setServiceProvider(String serviceProvider) {this.serviceProvider = serviceProvider;}
    public void setData(LinkedList<String> data) {this.Data = data;}
    public void setAmount(double amount) {this.amount = amount;}
    
    
    //getters
    public String getService() {return this.service;}
    public String getServiceProvider() {return this.serviceProvider;}
    public LinkedList<String> getData() {return this.Data;}
    public double getAmount() {return this.amount;}

}
