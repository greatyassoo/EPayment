

public class Wallet implements Payment{

    @Override
    public boolean Pay(Double amount) {
        System.out.println("Payment Done Using Cash from Wallet");
        return true;
    }
    
}