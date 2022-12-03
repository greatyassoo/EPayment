public class OverAllDiscount extends DiscountDecorator {
    OverAllDiscount(Discount payment,double ammount){this.payment=payment;this.ammount=ammount;discription = this.getClass().getSimpleName()+":"+ammount+" ";}
}
