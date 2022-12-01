public class ServiceProvider {
    private String name;
    private Form form;
    private Payment paymMethod;
    ServiceProvider(String name,Form form){
        this.name=name;
        this.form=form;
    }
    public void setPaymentMethod(Payment py){
        this.paymMethod=py;
    }

    public String getName(){return name;}
}
