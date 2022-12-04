public class DiscountController {
    private static double overAllDiscount = 0;
    private double[] servicesDiscounts = new double[Service.Names.values().length];

    private double discount = 0;

    DiscountController(){for(int i=0 ; i<servicesDiscounts.length ; i++) servicesDiscounts[i]=0;}

    public double getDiscount(Service.Names serviceName){
        verifyOverAllDiscount();
        verifyServiceDiscount(serviceName);
        return discount;
    }

    public static double getOverAllDiscount(){return overAllDiscount;}
    public double getServiceDiscount(Service.Names serviceName){return servicesDiscounts[serviceName.ordinal()];}

    public void setServiceDiscount(Service.Names serviceName,double ammount){servicesDiscounts[serviceName.ordinal()]=ammount;}
    public static boolean setOverAllDiscount(double ammount){if(ammount>0){overAllDiscount = ammount;return true;};return false;}

    private void verifyOverAllDiscount(){
        if(UserController.account.getTransactions().size()==0 && overAllDiscount>discount)
            discount=overAllDiscount; 
    }
    private void verifyServiceDiscount(Service.Names serviceName){
        if(servicesDiscounts[serviceName.ordinal()]> discount)
            discount=servicesDiscounts[serviceName.ordinal()];
    }
}
