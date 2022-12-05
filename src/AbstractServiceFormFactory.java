import java.util.LinkedList;
public interface AbstractServiceFormFactory{
    public Service createService(LinkedList<ServiceProvider> serviceProviders);
}