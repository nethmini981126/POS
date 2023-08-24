package dao;

import dao.custom.LoginDAO;
import dao.custom.impl.*;

public class DAOFactory {
    public static DAOFactory daoFactory;

    public enum DAOTypes{
        LOGIN, ITEM, CUSTOMER, ORDER, ORDERDETAILS
    }

    public static DAOFactory getDaoFactory(){
        if(daoFactory == null){
            return daoFactory = new DAOFactory();
        }else {
            return daoFactory;
        }
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case LOGIN:
                return new LoginDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailsDAOImpl();
            default:
                return null;
        }
    }

    private DAOFactory(){}

}
