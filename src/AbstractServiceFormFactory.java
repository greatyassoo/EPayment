import java.util.LinkedList;
public interface AbstractServiceFormFactory{
    public Service createService(LinkedList<String> serviceProviders);
    public TemplateForm createForm();
}