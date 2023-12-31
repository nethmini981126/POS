package dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ItemDTO {
    private String itemID;
    private String itemName;
    private String batchNumber;
    private double price;
    private double qty;
    private String supplier;
    private Date expDate;
}
