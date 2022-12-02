import java.util.LinkedList;

public class Service {
    private String name;
    private double initialDiscount = 0;
    private double serviceDiscount = 0;
    private LinkedList<ServiceProvider> serviceProviders;   
    private Form form;

    //Service(){}
    
    Service(String name, double initialDiscount , double serviceDiscount , LinkedList<ServiceProvider> serviceProviders){
        this.name = name;
        this.initialDiscount = initialDiscount;
        this.serviceDiscount = serviceDiscount;
        this.serviceProviders = serviceProviders;
        this.form = new Form();
    }

    //setters
    public void setName(String name){this.name=name;}
    public void setInitialDiscount(double initialDiscount){this.initialDiscount=initialDiscount;}
    public void setServiceDiscount(double serviceDiscount){this.serviceDiscount=serviceDiscount;}
    
    public boolean addServiceProvider(ServiceProvider serviceProvider){
        try {serviceProviders.addLast(serviceProvider);} 	
        catch (Exception e) {return false;}
        return true;
    }
    public boolean removeServiceProvider(int indx){
        try{serviceProviders.remove(indx);}
        catch(Exception e){return false;}
        return true;
    }

    //getters
    public String getName(){return name;}
    public double getInitialDiscount(){return initialDiscount;}
    public double getServiceDiscount(){return serviceDiscount;}
    public LinkedList<ServiceProvider> getServiceProviders(){return serviceProviders;}
    public void initForm(String serviceProviderName) {form.displayForm(name, serviceProviderName);}

}
