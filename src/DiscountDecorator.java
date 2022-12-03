public abstract class DiscountDecorator extends Discount {
    protected Discount payment;
    protected String discription;
    protected String name;
    public double getDiscount() {return payment.getDiscount()*ammount;}
    public String getInfo() {return payment.getInfo()+this.getClass().getSimpleName()+":"+ammount+" ";}
}
