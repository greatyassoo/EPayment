


public class Credit implements Payment{

    @Override
    public boolean Pay(Double amount) {
        System.out.println("Payment Done Using Credit Card");
        return true;
    }
    
}
