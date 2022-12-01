import java.util.LinkedList;

public class ServiceProvider {
    private String sname,pname;
    private LinkedList<String> Data = new LinkedList<String>();
    private Form form;
    private Payment paymMethod;
    ServiceProvider(String gsname,String gpname,LinkedList<String> gdata,Form form){
        this.sname=gsname;
        this.pname=gpname;
        this.Data=gdata;
        this.form=form;
    }
    public boolean handleRequest(){
        Transaction ntransaction=new Transaction(sname, pname,Data, 0);

    }
    public void setPaymentMethod(Payment py){
        this.paymMethod=py;
        paymMethod.Pay(0);
    }
}
