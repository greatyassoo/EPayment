public class CreditPaymentStrategy implements PaymentStrategy{
    @Override
    public void Pay(double amount) {
        System.out.println("Payment Done Using Credit Card");
        
    }
}
