public abstract class TemplateForm {
    private double amount,discount;
    private String phoneNumber;
    private FormHandler handler;
    private int paymentMethodIndex=0;
    boolean checkPayment;

    public TemplateForm(){this.handler = new FormHandler();}

    public void displayForm(String service, ServiceProvider serviceProviderName , double discount) // template method style function.
    {
        do{
            this.discount = discount;
            displayFormHeader(service); 
            displayServiceProviderName(serviceProviderName.getName()); // has different implementations between different concretions
            displayDiscount();
            getPhoneNumber();
            getAmount();
            checkPayment = displayPaymentMethods();
            displayFormHeader(service);
        }
        while(checkPayment == false);
    }

    // steps required to display form.

    private void displayDiscount() {System.out.print("Discount: "+discount+"\n");}

    private boolean displayPaymentMethods() {
        System.out.print("Choose payment method [default Credit card] (y/n): ");
        String c = Main.scanner.nextLine();
        if(c.toLowerCase().equals("n"));
        else{
            System.out.print("Available payment methods:-\n");
            for(int i=0 ; i<FormHandler.paymentMethods.length ; i++)
            System.out.print((i+1)+"-"+FormHandler.paymentMethods[i]+".\n");
            System.out.print("Choice: ");
            paymentMethodIndex = Integer.parseInt(Main.scanner.nextLine());
            paymentMethodIndex--;
        }

        int output = handler.evaluateForm(phoneNumber, amount, discount, paymentMethodIndex) ;  
        //0 for normal operation
        //-1 for negative amount
        //-2 for invalid payment method index
        //-3 for invalid phone number
        //-4 for paymentmethod error 
        if(output==-1)
            {System.out.print("Error, "+amount+" amount can't be negative.\n");
            return false;}
        else if(output==-2)
            {System.out.print("Invalid payment method selected.\n");
            return false;}
        else if(output==-3)
            {System.out.print(phoneNumber+" must be 11 digits only.\n");
            return false;}
        else if(output==-4)
            {System.out.print("Error with the selected payment method.\n");
            return false;}
        return true;
    }

    private void displayFormHeader(String serviceName) {
        System.out.println("=================================== " + serviceName + " Form ===================================");
    }
    
    protected abstract void displayServiceProviderName(String serviceProviderName);

    private void getPhoneNumber() {
        System.out.print("Enter Phone Number(11 digits only): ");
        phoneNumber = Main.scanner.nextLine();
    }

    private void getAmount() {
        System.out.print("Enter Amount: " );
        amount = Double.parseDouble(Main.scanner.nextLine());
    }
}
