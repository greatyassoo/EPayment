import java.util.LinkedList;

public class Service {
    private String name;
    public Discount discount;
    private LinkedList<String> serviceProviders;   
    private TemplateForm form;
    
    Service(String name, double initialDiscount , double serviceDiscount , LinkedList<String> serviceProviders){
        this.name = name;
        discount= new BaseDiscount(1);
        this.serviceProviders = serviceProviders;
    }

    Service(String name, double initialDiscount , double serviceDiscount , LinkedList<String> serviceProviders, TemplateForm form){
        this.name = name;
        discount= new BaseDiscount(1);
        this.serviceProviders = serviceProviders;
        this.form = form;
    }

    //setters
    public void setName(String name){this.name=name;}
    public void addOverAllDiscount(double discountAmmount){discount = new OverAllDiscount(discount, discountAmmount);}
    public void addServiceDiscount(double discountAmmount){discount = new ServiceDiscount(discount, discountAmmount);}
    
    public boolean addServiceProvider(String serviceProvider){
        try {serviceProviders.addLast(serviceProvider);} 	
        catch (Exception e) {return false;}
        return true;
    }

    //getters
    public String getName() {return name;}
    public String getDiscountsInfo() {return discount.getInfo();}
    public double getDiscount() {return discount.getDiscount();}
    public LinkedList<String> getServiceProviders(){return serviceProviders;}
    public TemplateForm getForm() {return this.form;}
    //public void initForm(String serviceProviderName) {form.displayForm(name, serviceProviderName);}

}
