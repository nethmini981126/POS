package bo;

import bo.custom.LoginBO;
import bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public enum BoTypes{
        LOGIN,ITEM,CUSTOMER, ORDER, ORDERDETAILS
    }

    public static BOFactory getBoFactory(){
        if (boFactory == null){
            return boFactory = new BOFactory();
        }else {
            return boFactory;
        }
    }

    public SuperBO getBo(BoTypes boTypes){
        switch (boTypes){
            case LOGIN:
                return new LoginBoImpl();
            case ITEM:
                return new ItemBoImpl();
            case CUSTOMER:
                return new CustomerBoImpl();
            case ORDER:
                return new OrderBoImpl();
            case ORDERDETAILS:
                return new OrderDetailsBoImpl();
            default:
                return null;
        }
    }
}
