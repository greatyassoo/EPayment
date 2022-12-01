import java.util.LinkedList;
import java.util.*;


public class FormTerminal {
    String sname,pname;
    private ServiceProvider spcontroller;
    private Payment payMethod;
    FormTerminal(String nsname,String npname){
        this.sname=nsname;
        this.pname=npname;
    }
    public void terminal(){
        System.out.println("Service Name : "+sname+"\n");
        System.out.println("ServiceProvider Name : "+pname+"\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Your Method Of Payment");
        String ans=scanner.nextLine();
        ServiceProvider myserviceprovider=new ServiceProvider(, null, null);

    }
    
}
