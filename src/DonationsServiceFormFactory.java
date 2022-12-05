import java.util.LinkedList;
public class DonationsServiceFormFactory implements AbstractServiceFormFactory{
    public Service createService(LinkedList<ServiceProvider> serviceProviders) {
        return new Service("Donations", serviceProviders,new DonationsForm());
    }
}