public class CashPayment implements Paymentype{
    @Override
    public boolean Pay(double ammount) {
        System.out.println("Payment Done Using Cash On Delivery ammount = "+ammount);
        return true;
    }
}
