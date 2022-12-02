import java.util.LinkedList;

public abstract class ServicesController {
    protected LinkedList<Service> services;
    protected Account account;

    public LinkedList<Service> searchServices(String name){
        LinkedList<Service> temp = new LinkedList<Service>();
        for(int i=0 ; i<services.size() ; i++){
            if(services.get(i).getName().toLowerCase().contains(name.toLowerCase()))
                temp.addLast(services.get(i));
        }
        return temp;
    }
    
    public LinkedList<Service> getServices(){return services;}
    public LinkedList<ServiceProvider> getServiceProviders(Service service){return service.getServiceProviders();}
    public int getServiceIndex(String sName) {
    	for(int i=0 ; i<services.size();i++)
    	{
    		if(this.services.get(i).getName().equals(sName))
    		{
    			return i;
    		}
    	}
    	return -1;
    }

}