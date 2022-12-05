import java.util.LinkedList;
public class MobileRechargeServiceFactory implements AbstractServiceFormFactory {
    public Service createService(LinkedList<ServiceProvider> serviceProviders) {
    	
        return new Service("Mobile Recharge Service", serviceProviders,new MobileRechargeForm());
    }
}