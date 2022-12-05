public class WalletPayment implements PaymentType{
    @Override
    public boolean Pay(double amount) {
        System.out.print("Payment Done Using Cash from Wallet.\n");
        if(!(UserController.account.getWalletBalance()>=amount))
            return false;
        UserController.account.setWalletBalance(UserController.account.getWalletBalance()-amount);
        return true;
    }  
}
