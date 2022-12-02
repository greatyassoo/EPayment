public class Form {

    private String serviceProviderName;
    private double amount;

    public Form() {} // have it defined the code wont scream at me. NEED TO REFACTOR CODE
    public Form(String serviceProviderName, double amount) {
        this.serviceProviderName = serviceProviderName;
        this.amount = amount;
    }



    // for now ill assume its MobileRechargeService using Vodafone

    public void displayFormHeader() {
        System.out.println("============================================= Mobile Recharge Form =============================================");
    }
    
    public void displayServiceProviderName() {
        System.out.println("Service Provider: " + serviceProviderName);
    }


    public void displayForm()
    {
        displayFormHeader();
        displayServiceProviderName();
        int phoneNumber;

    }
}