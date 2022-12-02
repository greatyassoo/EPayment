public class ServiceProvider {
    protected String name;
    protected Form form;
    ServiceProvider(){};
    ServiceProvider(String name,Form form){
        this.name=name;
        this.form=form;
    }
    public void setPaymentMethod(Payment py){
        this.paymMethod=py;
    }

    public String getName(){return name;}
}
