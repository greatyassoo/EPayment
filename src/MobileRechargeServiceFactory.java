import java.util.LinkedList;
public class MobileRechargeServiceFactory implements AbstractServiceFormFactory {
    public Service createService(double initialDiscount , double serviceDiscount , LinkedList<String> serviceProviders) {
        return new Service("Mobile Recharge Service", initialDiscount, serviceDiscount, serviceProviders);
    }

    public TemplateForm createForm() {return new MobileRechargeForm();} //returns mobileService form.
}