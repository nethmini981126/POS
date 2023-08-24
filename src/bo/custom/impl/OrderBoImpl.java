package bo.custom.impl;

import bo.custom.OrderBO;
import dao.DAOFactory;
import dao.custom.OrderDAO;
import dto.OrderDetailsDTO;

public class OrderBoImpl implements OrderBO {

    private OrderDAO orderDAO= (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public String generateNextOrderID() {
        String lastOrderID = orderDAO.getLastOrderID();
        int lastId = Integer.parseInt(lastOrderID.substring(1));
        return String.format("O%03d",++lastId);
    }
}
