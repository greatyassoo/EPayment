public class ServiceProvider {
    private String name;
    private PaymentStrategy paymentStrategy;

    public ServiceProvider(String name, PaymentStrategy paymentStrategy)
    {
        this.name = name; 
        this.paymentStrategy = paymentStrategy;
    }

    // setters
    public void setName(String name) {this.name = name;}
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {this.paymentStrategy = paymentStrategy;}

    //getters
    public String getName() {return this.name;}

    //functions
    public void pay(double amount) {this.paymentStrategy.Pay(amount);}
}
