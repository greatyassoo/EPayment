public class DonationsForm extends TemplateForm {
    @Override
    protected void displayServiceProviderName(String serviceProviderName) {
        System.out.println("Institute Donating to: " + serviceProviderName);
    }
}