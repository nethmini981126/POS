package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDetails {
    private String orderID;
    private String itemID;
    private double unitPrice;
    private double qty;
    private double  discountedAmount;
}
