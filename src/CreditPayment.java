public class CreditPayment implements Paymentype{
    @Override
    public boolean Pay(double ammount) {
        System.out.print("Payment Done Using Credit Card.\nCardNumber: ");
        String CCN = SingleScanner.getInstance().nextLine();
        System.out.print("PIN: ");
        String PIN = SingleScanner.getInstance().nextLine();
        if(!CCN.matches("-?\\d+(\\.\\d+)?") || CCN.length()!=16 || PIN.length()!=4 || PIN.matches("-?\\d+(\\.\\d+)?"))
            return false;
        return true;
    }
}
