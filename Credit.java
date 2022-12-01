


public class Credit implements Payment{

    @Override
    public boolean Pay(int amount) {
        System.out.println("Payment Done Using Credit Card");
        return true;
    }
    
}
