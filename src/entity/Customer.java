package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Customer {
    private String customerID;
    private String firstName;
    private String lastName;
    private String nic;
    private String address;
    private String contactNumber;
}
