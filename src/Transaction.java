public class Transaction {
    private String service, serviceProvider, data; // data is the unknown data that varies between different transactions.
    private double amount;

    Transaction(String service, String serviceProvider, String data, double amount) {
        this.service = service; 
        this.serviceProvider = serviceProvider;
        this.data = data;
        this.amount = amount;
    }
       
    
    //setters
    public void setService(String service) {this.service = service;}
    public void setServiceProvider(String serviceProvider) {this.serviceProvider = serviceProvider;}
    public void setData(String data) {this.data = data;}
    public void setAmount(double amount) {this.amount = amount;}
    
    
    //getters
    public String getService() {return this.service;}
    public String getServiceProvider() {return this.serviceProvider;}
    public String getData() {return this.data;}
    public double getAmount() {return this.amount;}

}
