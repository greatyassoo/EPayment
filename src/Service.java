import java.util.LinkedList;

public class Service {
    private String name;
    private double initialDiscount=0;
    private double serviceDiscount=0;
    private LinkedList<ServiceProvider> serviceProviders;

    Service(String name, double initialDiscount , double serviceDiscount , LinkedList<ServiceProvider> serviceProviders){
        this.name=name;
        this.initialDiscount=initialDiscount;
        this.serviceDiscount=serviceDiscount;
        this.serviceProviders=serviceProviders;
    }

    //setters
    public void setName(String name){this.name=name;}
    public void setInitialDiscount(Double initialDiscount){this.initialDiscount=initialDiscount;}
    public void setServiceDiscount(Double serviceDiscount){this.serviceDiscount=serviceDiscount;}
    
    public boolean addServiceProvider(ServiceProvider ServiceProvider){
        try {serviceProviders.addLast(ServiceProvider);} 
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
    public Double getInitialDiscount(){return initialDiscount;}
    public Double getServiceDiscount(){return serviceDiscount;}
    public LinkedList<ServiceProvider> getServiceProviders(){return serviceProviders;}

}
