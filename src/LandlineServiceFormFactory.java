import java.util.LinkedList;
public class LandlineServiceFormFactory implements AbstractServiceFormFactory{
    public Service createService(double initialDiscount , double serviceDiscount , LinkedList<String> serviceProviders) {
        TemplateForm landlineForm = createForm();
        return new Service("Landline", initialDiscount, serviceDiscount, serviceProviders, landlineForm);
    }

    public TemplateForm createForm() {return new LandlineForm();}
}