public class CashPayment implements Paymentype{
    @Override
    public boolean Pay(double amount) {
        System.out.println("Payment Done Using Cash On Delivery amount = "+amount);
        return true;
    }
}
