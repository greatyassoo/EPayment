public class ServiceNameException extends Exception{
    ServiceNameException(String name){
        super(name+" is an invalid Service.\n");
    }
}
