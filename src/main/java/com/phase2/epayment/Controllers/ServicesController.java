package com.phase2.epayment.Controllers;

import java.util.LinkedList;

import com.phase2.epayment.ServicesDB.*;

public abstract class ServicesController {
    protected ServicesDB servicesDB;
    //protected DiscountController discountController;

    public LinkedList<String> getServicesNames(String serviceName){
        LinkedList<String> temp = new LinkedList<String>();
        for(int i=0 ; i<servicesDB.size() ; i++){
            if(servicesDB.get(i).getName().toLowerCase().contains(serviceName.toLowerCase()))
                temp.addLast(servicesDB.get(i).getName());
        }
        return temp;
    }

    public LinkedList<ServiceProvider> getServiceProviders(String serviceName){
        LinkedList<ServiceProvider> temp = new LinkedList<ServiceProvider>();
        for(int i=0 ; i<servicesDB.size() ; i++){
            if(servicesDB.get(i).getName().toLowerCase().contains(serviceName.toLowerCase())){
                temp = servicesDB.get(i).getServiceProviders();
            }
        }
        return temp;
    }

    public int getServiceIndex(String sName) {
    	for(int i=0 ; i<servicesDB.size();i++)
    		if(this.servicesDB.get(i).getName().equals(sName))
    		    return i;
    	return -1;
    }

    public Service getService(int index){
        try{return servicesDB.get(index);}
        catch(Exception e){return null;}
    }

}
