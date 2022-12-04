import java.util.LinkedList;
public class DonationsServiceFormFactory implements AbstractServiceFormFactory{
    public Service createService(double initialDiscount , double serviceDiscount , LinkedList<String> serviceProviders) {
        TemplateForm doantionsForm = createForm();
        return new Service("Donations", initialDiscount, serviceDiscount, serviceProviders, doantionsForm);
    }

    public TemplateForm createForm() {return new DonationsForm();} //should return a DonationsService Form.
}