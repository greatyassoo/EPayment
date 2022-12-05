import java.util.LinkedList;
public class LandlineServiceFormFactory implements AbstractServiceFormFactory{
    public Service createService(LinkedList<ServiceProvider> serviceProviders) {
        return new Service("Landline",serviceProviders,new LandlineForm());
    }
}