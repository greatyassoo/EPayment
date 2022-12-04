public class CreditPayment implements Paymentype{
    @Override
    public boolean Pay(double amount) {
        System.out.print("Payment Done Using Credit Card.\nCardNumber(16 digit only): ");
        String CCN = Main.scanner.nextLine();
        System.out.print("PIN(4 digits only): ");
        String PIN = Main.scanner.nextLine();
        if(!CCN.matches("-?\\d+(\\.\\d+)?") || CCN.length()!=16 || PIN.length()!=4 || !PIN.matches("-?\\d+(\\.\\d+)?"))
            return false;
        return true;
    }
}
