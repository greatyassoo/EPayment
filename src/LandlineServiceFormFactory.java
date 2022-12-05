import java.util.LinkedList;
public class LandlineServiceFormFactory implements ServiceFormFactory{
    public Service createService(LinkedList<ServiceProvider> serviceProviders) {
        return new Service("Landline",serviceProviders,new LandlineForm());
    }
}