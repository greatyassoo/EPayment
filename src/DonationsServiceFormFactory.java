import java.util.LinkedList;
public class DonationsServiceFormFactory implements AbstractServiceFormFactory{
    public Service createService(LinkedList<String> serviceProviders) {
        TemplateForm doantionsForm = createForm();
        return new Service("Donations", serviceProviders, doantionsForm);
    }

    public TemplateForm createForm() {return new DonationsForm();} //should return a DonationsService Form.
}