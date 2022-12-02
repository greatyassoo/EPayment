import java.util.LinkedList;
public interface AbstractServiceFormFactory{
    public Service createService(double initialDiscount , double serviceDiscount , LinkedList<ServiceProvider> serviceProviders);
    public Form createForm();
}