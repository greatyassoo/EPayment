public class ServiceProviderNameException extends Exception{
    ServiceProviderNameException(String name){
        super(name+" is an invalid Service.\n");
    }
}
