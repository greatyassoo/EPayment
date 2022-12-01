import java.util.LinkedList;

public class UserController extends ServicesController{

    UserController(LinkedList<Service> services , Account account){
        this.services=services;
        this.account=account;
    }
}
