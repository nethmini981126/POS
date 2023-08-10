package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import dto.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class AddCustomerController {
    public TextField txtCustomerID;
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtNIC;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.CUSTOMER);

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        CustomerDTO customerDTO = new CustomerDTO(
                txtCustomerID.getText(),
                txtFirstName.getText(),
                txtLastName.getText(),
                txtNIC.getText(),
                txtAddress.getText(),
                txtContact.getText()
        );
        boolean b = customerBO.saveCustomer(customerDTO);
        System.out.println(b);
    }
}
