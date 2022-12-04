import java.util.LinkedList;
public class InternetServiceFormFactory implements AbstractServiceFormFactory {
    public Service createService(LinkedList<String> serviceProviders) {
        TemplateForm internetForm = createForm();
        return new Service("Internet Payment Service", serviceProviders, internetForm);
    }

    public TemplateForm createForm() {return new InternetForm();} //returns InternetPayment form.
}