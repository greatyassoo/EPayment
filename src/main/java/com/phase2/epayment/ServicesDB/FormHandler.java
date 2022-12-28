// package ServicesDB;

// import AccountsDB.*;
// import Controllers.*;

// public class FormHandler {
//     public static final String[] paymentMethods = {"Credit","Cash","Wallet"} ;

//     //0 for normal operation
//     //-1 for negative amount
//     //-2 for invalid payment method index
//     //-3 for invalid phone number
//     //-4 for paymentmethod error 
//     public int evaluateForm(String phoneNumber , double amount , double discount , int paymentMethodIndex){

//         if(amount<0)
//         {return -1;}
//         if(paymentMethodIndex>paymentMethods.length || paymentMethodIndex<0)
//         {return -2;}
//         if(!phoneNumber.matches("-?\\d+(\\.\\d+)?") || phoneNumber.length()!=11)
//         {return -3;}
//         PaymentType paymentType;

//         if(discount>0)
//             amount*=discount;

//         switch(paymentMethodIndex){
//             case 1 : 
//                 paymentType = new CashPayment();
//                 break;

//             case 2 :
//                 paymentType = new WalletPayment();
//                 break;
            
//             default : 
//                 paymentType = new CreditPayment();
//                 break;
//         }
//         if(!paymentType.Pay(String.valueOf(amount)))
//             {return -4;}

//         Transaction transaction = new Transaction(Transaction.TYPE.PAYMENT, UserTerminal.currentService, UserTerminal.currentServiceProvider.getName(), paymentMethods[paymentMethodIndex], phoneNumber, amount, discount);
//         UserController.account.addTransaction(transaction);
//         return 0;
//     }
// }
