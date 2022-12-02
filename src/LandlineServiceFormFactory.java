import java.util.LinkedList;
public class LandlineServiceFormFactory implements AbstractServiceFormFactory{
    public Service createService(double initialDiscount , double serviceDiscount , LinkedList<ServiceProvider> serviceProviders) {
        return new Service("Landline", initialDiscount, serviceDiscount, serviceProviders);
    }

    public Form createForm() {return new Form();} //should return a LandlineService Form.A
}