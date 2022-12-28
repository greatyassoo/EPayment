package com.phase2.epayment.ServicesDB;

import java.util.LinkedList;
import org.springframework.stereotype.Component;


@Component
public class ServicesDB {
    
    private LinkedList<Service> services;
    
    public ServicesDB(LinkedList<Service> services) {
        this.services = services;
        initDB();
    }

    public int size(){return services.size();}

    public Service get(int index){return services.get(index);}

    public LinkedList<Service> getAllServices(){return this.services;}

    private void initDB(){
        LinkedList<ServiceProvider> mobileRechargeProviders = new LinkedList<ServiceProvider>();
        mobileRechargeProviders.addLast(new ServiceProvider("Vodafone"));
        mobileRechargeProviders.addLast(new ServiceProvider("Etisalat"));
        mobileRechargeProviders.addLast(new ServiceProvider("Orange"));
        mobileRechargeProviders.addLast(new ServiceProvider("WE"));
        Service mobileRechargeService = new Service("Mobile Recharge Service", mobileRechargeProviders);

        LinkedList<ServiceProvider> internetProviders = new LinkedList<ServiceProvider>();
        internetProviders.addLast(new ServiceProvider("Vodafone"));
        internetProviders.addLast(new ServiceProvider("Etisalat"));
        internetProviders.addLast(new ServiceProvider("Orange"));
        internetProviders.addLast(new ServiceProvider("WE"));
        Service internetService = new Service("Internet Service", internetProviders);

        LinkedList<ServiceProvider> landLineProviders = new LinkedList<ServiceProvider>();
        landLineProviders.addLast(new ServiceProvider("Monthly receipt"));
        landLineProviders.addLast(new ServiceProvider("Quarter receipt"));
        Service landLineService = new Service("Landline Service", landLineProviders);

        LinkedList<ServiceProvider> donationsProviders = new LinkedList<ServiceProvider>();
        donationsProviders.addLast(new ServiceProvider("Cancer Hospital"));
        donationsProviders.addLast(new ServiceProvider("Schools"));
        donationsProviders.addLast(new ServiceProvider("NGOs (Non profitable organizations)"));
        Service donationsService = new Service("Donations Service", donationsProviders);
        
        services.addLast(mobileRechargeService);
        services.addLast(internetService);
        services.addLast(landLineService);
        services.addLast(donationsService);
    }
}