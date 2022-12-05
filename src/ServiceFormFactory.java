import java.util.LinkedList;
public interface ServiceFormFactory{
    public Service createService(LinkedList<ServiceProvider> serviceProviders);
}