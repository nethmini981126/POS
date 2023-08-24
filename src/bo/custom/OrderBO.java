package bo.custom;

import bo.SuperBO;
import dto.ItemDTO;
import dto.OrderDetailsDTO;

public interface OrderBO extends SuperBO {
    public String generateNextOrderID();


}
