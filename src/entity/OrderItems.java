package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderItems {
    private String orderID;
    private String itemID;
    private Double RequestQty;
    private Double unitPrice;
    private Double discount;
    private Double itemTotal;
}
