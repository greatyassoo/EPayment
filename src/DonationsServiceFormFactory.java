import java.util.LinkedList;
public class DonationsServiceFormFactory implements AbstractServiceFormFactory{
    public Service createService(double initialDiscount , double serviceDiscount , LinkedList<ServiceProvider> serviceProviders) {
        return new Service("Donations", initialDiscount, serviceDiscount, serviceProviders);
    }

    public Form createForm() {return new Form();} //should return a DonationsService Form.
}