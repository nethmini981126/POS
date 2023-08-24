package bo.custom.impl;

import bo.custom.OrderDetailsBO;
import dao.DAOFactory;
import dao.custom.OrderDetailsDAO;
import dto.OrderDetailsDTO;
import entity.Item;
import entity.OrderDetails;

public class OrderDetailsBoImpl implements OrderDetailsBO {

    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    @Override
    public boolean saveOrderDetails(OrderDetailsDTO orderDetailsDTO) {
        // Create OrderDetails entity
        OrderDetails orderDetails = new OrderDetails(
                orderDetailsDTO.getOrderID(),
                orderDetailsDTO.getItemID(),
                orderDetailsDTO.getUnitPrice(),
                orderDetailsDTO.getQty(),
                orderDetailsDTO.getDiscountedAmount()
        );
        //save item via ItemDAO
        return orderDetailsDAO.saveOrderDetails(orderDetails);
    }
}
