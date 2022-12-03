import java.util.LinkedList;
public class InternetPaymentServiceFactory implements AbstractServiceFormFactory {
    public Service createService(double initialDiscount , double serviceDiscount , LinkedList<String> serviceProviders) {
        return new Service("Internet Payment Service", initialDiscount, serviceDiscount, serviceProviders);
    }

    public TemplateForm createForm() {return new InternetForm();} //returns InternetPayment form.
}