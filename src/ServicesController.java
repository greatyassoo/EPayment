import java.util.LinkedList;

public abstract class ServicesController {
    protected LinkedList<Service> services;

    public LinkedList<String> getServicesNames(String serviceName){
        LinkedList<String> temp = new LinkedList<String>();
        for(int i=0 ; i<services.size() ; i++){
            if(services.get(i).getName().toLowerCase().contains(serviceName.toLowerCase()))
                temp.addLast(services.get(i).getName());
        }
        return temp;
    }

    public LinkedList<String> getServiceProviders(String serviceName){
        LinkedList<String> temp = new LinkedList<String>();
        LinkedList<ServiceProvider> tServiceProviders = new LinkedList<ServiceProvider>();
        for(int i=0 ; i<services.size() ; i++){
            if(services.get(i).getName().toLowerCase().contains(serviceName.toLowerCase())){
                tServiceProviders = services.get(i).getServiceProviders();
            }
        }

        for(int i=0 ; i<tServiceProviders.size() ; i++){
            temp.addLast(tServiceProviders.get(i).getName());
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
