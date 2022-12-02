// Format : Service:ServiceProvider:Ammount:PaymentMethod:Discount:Data

public class Transaction {
    private String service, serviceProvider, data, paymentMethod; // data is the unknown data that varies between different transactions.
    private double amount,discount;
    public static enum format {service,serviceProvider,ammount,paymentMethod,data};

    Transaction(String service, String serviceProvider, String data, String paymentMethod, double amount, double discount) {
        this.service = service; 
        this.serviceProvider = serviceProvider;
        this.data = data;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.discount = discount;
    }
       
    
    //setters
    public void setService(String service) {this.service = service;}
    public void setServiceProvider(String serviceProvider) {this.serviceProvider = serviceProvider;}
    public void setData(String data) {this.data = data;}
    public void setPaymentMethod(String paymentMethod) {this.paymentMethod = paymentMethod;}
    public void setAmount(double amount) {this.amount = amount;}
    public void setDiscount(double discount) {this.discount = discount;}
    
    
    //getters
    public String getService() {return this.service;}
    public String getServiceProvider() {return this.serviceProvider;}
    public String getData() {return this.data;}
    public String getPaymentMethod() {return this.paymentMethod;}
    public double getAmount() {return this.amount;}
    public double getDiscount() {return this.discount;}
    
}
