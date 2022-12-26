import java.util.LinkedList;

public class ServicesDB {
    
    private LinkedList<Service> services;

    //@AutoWired
    public ServicesDB(LinkedList<Service> services) {this.services = services; }

    public int size(){
        return services.size();
    }

    public Service get(int index){
        return services.get(index);
    }

    public void initDB(){
        LinkedList<ServiceProvider> sp1 = new LinkedList<ServiceProvider>();
        sp1.addLast(new ServiceProvider("Vodafone"));
        sp1.addLast(new ServiceProvider("Etisalat"));
        sp1.addLast(new ServiceProvider("Orange"));
        sp1.addLast(new ServiceProvider("WE"));
        Service s1 = new MobileRechargeServiceFactory().createService(sp1);

        LinkedList<ServiceProvider> sp2 = new LinkedList<ServiceProvider>();
        sp2.addLast(new ServiceProvider("Vodafone"));
        sp2.addLast(new ServiceProvider("Etisalat"));
        sp2.addLast(new ServiceProvider("Orange"));
        sp2.addLast(new ServiceProvider("WE"));
        Service s2 = new InternetServiceFormFactory().createService(sp2);

        LinkedList<ServiceProvider> sp3 = new LinkedList<ServiceProvider>();
        sp3.addLast(new ServiceProvider("Monthly receipt"));
        sp3.addLast(new ServiceProvider("Quarter receipt"));
        Service s3 = new LandlineServiceFormFactory().createService(sp3);

        LinkedList<ServiceProvider> sp4 = new LinkedList<ServiceProvider>();
        sp4.addLast(new ServiceProvider("Cancer Hospital"));
        sp4.addLast(new ServiceProvider("Schools"));
        sp4.addLast(new ServiceProvider("NGOs (Non profitable organizations)"));
        Service s4 = new DonationsServiceFormFactory().createService(sp4);
        
        services.addLast(s1);
        services.addLast(s2);
        services.addLast(s3);
        services.addLast(s4);
    }
    


}
