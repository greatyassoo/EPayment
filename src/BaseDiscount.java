public class BaseDiscount extends Discount {
    BaseDiscount(double a){ammount=a;}
    @Override
    public double getDiscount() {return ammount;}
    public String getInfo() {return this.getClass().getSimpleName()+":"+ammount+" ";}
}
