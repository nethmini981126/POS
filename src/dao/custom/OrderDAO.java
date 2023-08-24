package dao.custom;

import dao.SuperDAO;
import entity.Item;
import entity.OrderDetails;

public interface OrderDAO extends SuperDAO {
    public String getLastOrderID();

}
