import java.util.LinkedList;

public class ServiceProvider {
    protected String sName;
    protected String spName;
    protected LinkedList<String> Data = new LinkedList<String>();
    protected double amount;
    private Payment paymMethod;
    private Form form;
    ServiceProvider(){}
    ServiceProvider(String sName,String spName,Form s){
        this.sName=sName;
        this.spName=spName;
        form = s;
        String tmpValue = form.getFirst().getValue();
        amount = Double.parseDouble(tmpValue);
        Data.addFirst("-Textbox values: ");
        for(int i=0;i<form.getTSize();i++)
        {
            Data.addLast(form.textBoxes.get(i).getValue());
        }
        Data.addLast(" -Dropdown values: ");
        for(int i=0;i<form.getDSize();i++)
        {
            Data.addLast(form.textBoxes.get(i).getValue());
        }
    }
    public boolean handleRequest(){
        Transaction ntransaction=new Transaction(sName, spName,Data, amount);
        return true;
    }
    public void setPaymentMethod(Payment py){
        this.paymMethod=py;
        paymMethod.Pay(amount);
    }
}
