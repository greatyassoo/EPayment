public class Form {

    // private String serviceProviderName;
    private double amount;
    private int phoneNumber;

    public Form() {} // have it defined the code wont scream at me. NEED TO REFACTOR CODE


    
    
    public void displayForm(String service, String serviceProviderName) // template method style function.
    {
        displayFormHeader(service);
        displayServiceProviderName(serviceProviderName);
        getPhoneNumber();
        getAmount();
        displayCost();
        displayFormHeader(service);
    }

    // steps required to display form.

    private void displayFormHeader(String serviceName) {
        System.out.println("=================================== " + serviceName +  " Form ===================================");
    }
    
    private void displayServiceProviderName(String serviceProviderName) {
        System.out.println("Service Provider: " + serviceProviderName);
    }

    private void displayCost(){ // cost is amount + 7% vat.
        System.out.println("Total Cost: " + (amount + (amount * 0.7)));
    }

    private void getPhoneNumber() {
        System.out.println("Enter Phone Number: ");
        phoneNumber = SingleScanner.getInstance().nextInt();
    }

    private void getAmount() {
        System.out.println("Enter Amount: " );
        amount = SingleScanner.getInstance().nextDouble();
    }

}