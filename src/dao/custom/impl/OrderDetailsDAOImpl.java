package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailsDAO;
import entity.OrderDetails;

import java.sql.SQLException;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public boolean saveOrderDetails(OrderDetails orderDetails) {
        try{
            return CrudUtil.executeUpdate("INSERT INTO orderdetails VALUES(?,?,?,?,?)",
                    orderDetails.getOrderID(),
                    orderDetails.getItemID(),
                    orderDetails.getUnitPrice(),
                    orderDetails.getQty(),
                    orderDetails.getDiscountedAmount()
            );
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
