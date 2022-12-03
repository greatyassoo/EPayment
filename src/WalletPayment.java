public class WalletPayment implements Paymentype{
    @Override
    public boolean Pay(double ammount) {
        System.out.print("Payment Done Using Cash from Wallet.\n");
        if(!(UserController.account.getWalletBalance()>=ammount))
            return false;
        UserController.account.setWalletBalance(UserController.account.getWalletBalance()-ammount);
        return true;
    }  
}
