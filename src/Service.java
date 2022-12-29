import java.util.LinkedList;

public class Service {

    public static enum Names {MobileRecharge,Landline,InternetRecharge,Donations};
    private String name;
    private LinkedList<ServiceProvider> serviceProviders;

    Service(String name, LinkedList<ServiceProvider> serviceProviders,TemplateForm form){
        this.name = name;
        this.serviceProviders = serviceProviders;
        setForm(form);
    }

    //setters
    public void setName(String name){this.name=name;}
    
    public void setForm(TemplateForm form)
    {
    	for(int i=0;i<serviceProviders.size();i++)
    	{
    		serviceProviders.get(i).setForm(form);
    	}
    }
    
    public boolean addServiceProvider(ServiceProvider serviceProvider){
        try {serviceProviders.addLast(serviceProvider);} 	
        catch (Exception e) {return false;}
        return true;
    }

    //getters
    public String getName() {return name;}
    public LinkedList<ServiceProvider> getServiceProviders(){return serviceProviders;}
    public TemplateForm getForm() {return serviceProviders.get(0).getForm();}

}
