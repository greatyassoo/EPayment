import java.util.LinkedList;

public class Service {

    public static enum Names {MobileRecharge,Landline,InternetRecharge,Donations};
    private String name;
    private LinkedList<String> serviceProviders;   
    private TemplateForm form;

    Service(String name, LinkedList<String> serviceProviders, TemplateForm form){
        this.name = name;
        this.serviceProviders = serviceProviders;
        this.form = form;
    }

    //setters
    public void setName(String name){this.name=name;}
    
    public boolean addServiceProvider(String serviceProvider){
        try {serviceProviders.addLast(serviceProvider);} 	
        catch (Exception e) {return false;}
        return true;
    }

    //getters
    public String getName() {return name;}
    public LinkedList<String> getServiceProviders(){return serviceProviders;}
    public TemplateForm getForm() {return this.form;}

}
