public abstract class TemplateForm {
    private double amount,discount;
    private String phoneNumber;
    private FormHandler handler;
    private int paymentMethodIndex=0;

    public TemplateForm(){this.handler = new FormHandler();}

    public void displayForm(String service, String serviceProviderName , double discount) // template method style function.
    {
        this.discount = discount;
        displayFormHeader(service); 
        displayServiceProviderName(serviceProviderName); // has different implementations between different concretions
        displayDiscount();
        getPhoneNumber();
        getAmount();
        displayPaymentMethods();
        displayFormHeader(service);
    }

    // steps required to display form.

    private void displayDiscount() {
        
    }

    private void displayPaymentMethods() {
        System.out.print("Choose payment method [default Credit card] (y/n): ");
        char c = SingleScanner.getInstance().next().charAt(0);
        if(Character.toLowerCase(c)=='n');
        else{
            System.out.print("Available payment methods:-\n");
            for(int i=0 ; i<FormHandler.paymentMethods.length ; i++)
            System.out.print((i+1)+"-"+FormHandler.paymentMethods[i]+".\n");
            System.out.print("Choice: ");
            paymentMethodIndex = SingleScanner.getInstance().nextInt();
            paymentMethodIndex--;
        }

        int output = handler.evaluateForm(phoneNumber, amount, discount, paymentMethodIndex) ;
        if(output==1)
            System.out.print("Error, invalid input/s.\n");
    }

    private void displayFormHeader(String serviceName) {
        System.out.println("=================================== " + serviceName + " Form ===================================");
    }
    
    protected abstract void displayServiceProviderName(String serviceProviderName);

    private void getPhoneNumber() {
        System.out.println("Enter Phone Number: ");
        phoneNumber = SingleScanner.getInstance().nextLine();
    }

    private void getAmount() {
        System.out.println("Enter Amount: " );
        amount = SingleScanner.getInstance().nextDouble();
    }
}
