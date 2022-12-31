package com.phase2.epayment.ServicesDB;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.phase2.epayment.Payment.CashPayment;
import com.phase2.epayment.Payment.CreditPayment;
import com.phase2.epayment.Payment.PaymentType;
import com.phase2.epayment.Payment.WalletPayment;


@Component
public class ServicesDB {
    
    private LinkedList<Service> services;
    
    public ServicesDB(LinkedList<Service> services) {
        this.services = services;
    }

    public int size(){return services.size();}
    public Service get(int index){return services.get(index);}
    public LinkedList<Service> getAllServices(){return this.services;}
    public LinkedList<Service> getServices(String serviceName){
        LinkedList<Service> temp = new LinkedList<>();
        for (int i = 0; i < services.size(); i++){
            if (services.get(i).getName().toLowerCase().contains(serviceName.toLowerCase()))
                temp.addLast(services.get(i));
        }
        return temp;
    }
    
    @Autowired
    private void initDB(){

        Discount discount = new Discount();
        
        LinkedList<PaymentType> acceptedPayments = new LinkedList<>();
        acceptedPayments.addLast(new CashPayment());
        acceptedPayments.addLast(new CreditPayment());
        acceptedPayments.addLast(new WalletPayment());
        LinkedList<ServiceProvider> mobileRechargeProviders = new LinkedList<ServiceProvider>();

        mobileRechargeProviders.addLast(new ServiceProvider("Vodafone"));
        mobileRechargeProviders.addLast(new ServiceProvider("Etisalat"));
        mobileRechargeProviders.addLast(new ServiceProvider("Orange"));
        mobileRechargeProviders.addLast(new ServiceProvider("WE"));
        Service mobileRechargeService = new Service("Mobile Recharge Service", mobileRechargeProviders,discount,acceptedPayments);

        LinkedList<ServiceProvider> internetProviders = new LinkedList<ServiceProvider>();
        internetProviders.addLast(new ServiceProvider("Vodafone"));
        internetProviders.addLast(new ServiceProvider("Etisalat"));
        internetProviders.addLast(new ServiceProvider("Orange"));
        internetProviders.addLast(new ServiceProvider("WE"));
        Service internetService = new Service("Internet Service", internetProviders,discount,acceptedPayments);

        LinkedList<ServiceProvider> landLineProviders = new LinkedList<ServiceProvider>();
        landLineProviders.addLast(new ServiceProvider("Monthly receipt"));
        landLineProviders.addLast(new ServiceProvider("Quarter receipt"));
        Service landLineService = new Service("Landline Service", landLineProviders,discount,acceptedPayments);

        LinkedList<ServiceProvider> donationsProviders = new LinkedList<ServiceProvider>();
        donationsProviders.addLast(new ServiceProvider("Cancer Hospital"));
        donationsProviders.addLast(new ServiceProvider("Schools"));
        donationsProviders.addLast(new ServiceProvider("NGOs (Non profitable organizations)"));
        Service donationsService = new Service("Donations Service", donationsProviders,discount,acceptedPayments);
        
        services.addLast(mobileRechargeService);
        services.addLast(internetService);
        services.addLast(landLineService);
        services.addLast(donationsService);
    }
}