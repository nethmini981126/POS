package dao.custom;

import dao.SuperDAO;
import entity.Item;
import entity.OrderDetails;

public interface OrderDetailsDAO extends SuperDAO {

    public boolean saveOrderDetails(OrderDetails orderDetails);


}
