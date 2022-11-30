import java.util.LinkedList;

public class ServicesController {
    protected LinkedList<Service> services;

    ServicesController(){
        services = new LinkedList<Service>();
    }

    public LinkedList<Service> searchServices(String name){
        LinkedList<Service> temp = new LinkedList<Service>();
        for(int i=0 ; i<services.size() ; i++){
            if(services.get(i).getName().toLowerCase().contains(name.toLowerCase()))
                temp.addLast(services.get(i));
        }
        return temp;
    }

    public LinkedList<Service> getServices(){return services;}
    public LinkedList<FormHandler> getServiceProviders(Service service){return service.getServiceProviders();}

}
