public class CashPaymentStrategy implements PaymentStrategy{

    @Override
    public void Pay(double amount) {
        System.out.println("Payment Done Using Cash On Delivery");
        
    }
    
}
