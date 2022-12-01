

public class Cash implements Payment{

    @Override
    public boolean Pay(int amount) {
        System.out.println("Payment Done Using Cash On Delivery");
        return true;
    }
    
}
