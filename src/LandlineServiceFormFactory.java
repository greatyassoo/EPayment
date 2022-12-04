import java.util.LinkedList;
public class LandlineServiceFormFactory implements AbstractServiceFormFactory{
    public Service createService(LinkedList<String> serviceProviders) {
        TemplateForm landlineForm = createForm();
        return new Service("Landline",serviceProviders, landlineForm);
    }
    public TemplateForm createForm() {return new LandlineForm();}
}