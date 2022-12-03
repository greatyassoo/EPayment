import java.util.LinkedList;
public class LandlineServiceFormFactory implements AbstractServiceFormFactory{
    public Service createService(double initialDiscount , double serviceDiscount , LinkedList<String> serviceProviders) {
        return new Service("Landline", initialDiscount, serviceDiscount, serviceProviders);
    }

    public TemplateForm createForm() {return new LandlineForm();} //should return a LandlineService Form.A
}