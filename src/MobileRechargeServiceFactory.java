import java.util.LinkedList;
public class MobileRechargeServiceFactory implements AbstractServiceFormFactory {
    public Service createService(LinkedList<String> serviceProviders) {
        TemplateForm mobileRechargeForm = createForm();
        return new Service("Mobile Recharge Service", serviceProviders, mobileRechargeForm);
    }
    public TemplateForm createForm() {return new MobileRechargeForm();} //returns mobileService form.
}