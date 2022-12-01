

public class Wallet implements Payment{

    @Override
    public boolean Pay(int amount) {
        System.out.println("Payment Done Using Cash from Wallet");
        return true;
    }
    
}