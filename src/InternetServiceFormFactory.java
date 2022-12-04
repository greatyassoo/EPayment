import java.util.LinkedList;
public class InternetServiceFormFactory implements AbstractServiceFormFactory {
    public Service createService(double initialDiscount , double serviceDiscount , LinkedList<String> serviceProviders) {
        TemplateForm internetForm = createForm();
        return new Service("Internet Payment Service", initialDiscount, serviceDiscount, serviceProviders, internetForm);
    }

    public TemplateForm createForm() {return new InternetForm();} //returns InternetPayment form.
}