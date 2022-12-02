public class WalletPaymentStrategy implements PaymentStrategy{
    @Override
    public void Pay(double amount) {
        System.out.println("Payment Done Using Cash from Wallet");
    }  
}
