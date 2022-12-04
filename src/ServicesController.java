import java.util.LinkedList;

public abstract class ServicesController {
    protected LinkedList<Service> services;
    protected DiscountController discountController;

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
        for(int i=0 ; i<services.size() ; i++){
            if(services.get(i).getName().toLowerCase().contains(serviceName.toLowerCase())){
                temp = services.get(i).getServiceProviders();
            }
        }
        return temp;
    }

    public int getServiceIndex(String sName) {
    	for(int i=0 ; i<services.size();i++)
    		if(this.services.get(i).getName().equals(sName))
    		    return i;
    	return -1;
    }

    public Service getService(int index){
        try{return services.get(index);}
        catch(Exception e){return null;}
    }

}
