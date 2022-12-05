public class FormHandler {
    public static final String[] paymentMethods = {"Credit","Cash","Wallet"} ;

    //0 for normal operation
    //-1 for negative amount
    //-2 for invalid payment method index
    //-3 for invalid phone number
    //-4 for paymentmethod error 
    public int evaluateForm(String phoeNumber , double amount , double discount , int paymentMethodIndex){

        if(amount<0)
        {return -1;}
        if(paymentMethodIndex>paymentMethods.length || paymentMethodIndex<0)
        {return -2;}
        if(!phoeNumber.matches("-?\\d+(\\.\\d+)?") || phoeNumber.length()!=11)
        {return -3;}
        Paymentype paymentype;

        switch(paymentMethodIndex){
            case 1 : 
                paymentype = new CashPayment();
                break;

            case 2 :
                paymentype = new WalletPayment();
                break;
            
            default : 
                paymentype = new CreditPayment();
                break;
        }
        if(!paymentype.Pay(amount))
            {return -4;}

        Transaction transaction = new Transaction(Transaction.TYPE.PAYMENT, UserTerminal.currentService, UserTerminal.currentServiceProvider.getName(), paymentMethods[paymentMethodIndex], phoeNumber, amount, discount);
        UserController.account.addTransaction(transaction);
        return 0;
    }
}
