import java.util.LinkedList;
public class InternetServiceFormFactory implements AbstractServiceFormFactory {
    public Service createService(LinkedList<ServiceProvider> serviceProviders) {
    	
        return new Service("Internet Payment Service", serviceProviders,new InternetForm());
    }
}