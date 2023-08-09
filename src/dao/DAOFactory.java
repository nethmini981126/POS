package dao;

import dao.custom.LoginDAO;
import dao.custom.impl.LoginDAOImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;

    public enum DAOTypes{
        LOGIN
    }

    public static DAOFactory getDaoFactory(){
        if(daoFactory == null){
            return daoFactory = new DAOFactory();
        }else {
            return daoFactory;
        }
    }

    public LoginDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case LOGIN:
                return new LoginDAOImpl();
            default:
                return null;
        }
    }

    private DAOFactory(){}

}
