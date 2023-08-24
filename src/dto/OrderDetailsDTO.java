package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderDetailsDTO {
    private String orderID;
    private String itemID;
    private double unitPrice;
    private double qty;
    private double  discountedAmount;
}
