import java.util.LinkedList;

public abstract class ServicesController {
    protected LinkedList<Service> services;

    public LinkedList<Service> getServices(String name){
        LinkedList<Service> temp = new LinkedList<Service>();
        for(int i=0 ; i<services.size() ; i++){
            if(services.get(i).getName().toLowerCase().contains(name.toLowerCase()))
                temp.addLast(services.get(i));
        }
        return temp;
    }

    public LinkedList<Service> getServices(){return services;}
    public LinkedList<ServiceProvider> getServiceProviders(Service service){return service.getServiceProviders();}

}
