import java.util.LinkedList;
public interface AbstractServiceFormFactory{
    public Service createService(double initialDiscount , double serviceDiscount , LinkedList<String> serviceProviders);
    public TemplateForm createForm();
}