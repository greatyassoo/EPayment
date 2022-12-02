

public class Cash implements Payment{

    @Override
    public boolean Pay(Double amount) {
        System.out.println("Payment Done Using Cash On Delivery");
        return true;
    }
    
}
