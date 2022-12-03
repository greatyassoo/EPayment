public class ServiceDiscount extends DiscountDecorator{
    ServiceDiscount(Discount payment,double ammount){
        this.payment=payment;
        this.ammount=ammount;
        discription="ServiceDiscount:"+ammount+" ";
        
    }
}
