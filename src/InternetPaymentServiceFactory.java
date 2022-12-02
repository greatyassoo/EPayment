import java.util.LinkedList;
public class InternetPaymentServiceFactory implements AbstractServiceFormFactory {
    public Service createService(double initialDiscount , double serviceDiscount , LinkedList<ServiceProvider> serviceProviders) {
        return new Service("Internet Payment Service", initialDiscount, serviceDiscount, serviceProviders);
    }

    public Form createForm() {return new Form();} //returns InternetPayment form.
}