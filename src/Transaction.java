// Format : Service:ServiceProvider:Ammount:PaymentMethod:Discount:Data

public class Transaction {
    public static enum TYPE {PAYMENT, REFUND, TOP_UP}

    private String service, serviceProvider , paymentMethod , phoneNumber; // data is the unknown data that varies between different transactions.
    private double ammount, discount;
    private TYPE type;

    public Transaction() {};
    public Transaction(TYPE type, String service, String serviceProvider, String paymentMethod, String phoneNumber, double ammount, double discount) {
        this.service = service; 
        this.serviceProvider = serviceProvider;
        this.paymentMethod = paymentMethod;
        this.phoneNumber = phoneNumber;
        this.ammount = ammount;
        this.discount = discount;
    }
       
    
    //setters
    public void setService(String service) {this.service = service;}
    public void setServiceProvider(String serviceProvider) {this.serviceProvider = serviceProvider;}
    public void setPaymentMethod(String paymentMethod) {this.paymentMethod = paymentMethod;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setAmount(double ammount) {this.ammount = ammount;}
    public void setDiscount(double discount) {this.discount = discount;}
    public void setType(TYPE type) {this.type = type;}
    
    
    //getters
    public TYPE getType() {return this.type;}
    public String getService() {return this.service;}
    public String getServiceProvider() {return this.serviceProvider;}
    public String getPaymentMethod() {return this.paymentMethod;}
    public String getPhoneNumber() {return this.phoneNumber;}
    public double getAmount() {return this.ammount;}
    public double getDiscount() {return this.discount;}
    public String getAllInfo() {return type+":"+service+":"+serviceProvider+":"+ammount+":"+paymentMethod;}
    
}
