public class ServiceProvider {
    protected String name;
    protected Form form;
    ServiceProvider(){};
    protected Payment paymentMethod;
    ServiceProvider(String name,Form form){
        this.name=name;
        this.form=form;
    }
    public void setPaymentMethod(Payment paymentMethod){this.paymentMethod=paymentMethod;}

    public String getName(){return name;}
}
