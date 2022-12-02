import java.util.LinkedList;
public class MobileRechargeServiceFactory implements AbstractServiceFormFactory {
    public Service createService(double initialDiscount , double serviceDiscount , LinkedList<ServiceProvider> serviceProviders) {
        return new Service("Mobile Recharge Service", initialDiscount, serviceDiscount, serviceProviders);
    }

    public Form createForm() {return new Form();} //returns mobileService form.
}