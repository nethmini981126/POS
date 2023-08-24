package bo.custom;

import bo.SuperBO;
import dto.ItemDTO;
import dto.OrderDetailsDTO;

public interface OrderDetailsBO extends SuperBO {

    public boolean saveOrderDetails(OrderDetailsDTO orderDetailsDTO);
}
