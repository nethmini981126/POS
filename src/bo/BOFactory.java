package bo;

import bo.custom.LoginBO;
import bo.custom.impl.CustomerBoImpl;
import bo.custom.impl.ItemBoImpl;
import bo.custom.impl.LoginBoImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public enum BoTypes{
        LOGIN,ITEM,CUSTOMER
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
            default:
                return null;
        }
    }
}
