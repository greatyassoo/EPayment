import java.util.Scanner;

// used to be able to call the same scanner instance throughout the system, making it accessible anywhere and avoiding constructing multiple scanners. 
public class SingleScanner {
    private static Scanner sc = null;
    private SingleScanner(){
        sc = new Scanner(System.in);
    }
    public static Scanner getInstance(){
        if(sc==null)
            sc = new Scanner(System.in);
        return sc;
    }
}