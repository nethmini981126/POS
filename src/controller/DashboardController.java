package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import dto.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class DashboardController {
    public TableView tblallitems;
    public Label lblCustomerName;
    public Label lblCustomerAddress;
    public Label lblNIC;
    public TextField txtContactNumber;
    public Label lblCustomerID;
    public Button btnFind;
    public TextField txtDescription;
    public TextField txtRequestAmount;
    public TextField txtDiscount;
    private CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.CUSTOMER);

    public void btnFindOnAction(ActionEvent actionEvent) {
        //Check the customer from contact number
        CustomerDTO searchCustomer = customerBO.getCustomerByContactNumber(txtContactNumber.getText());
        if(searchCustomer != null){
            lblCustomerID.setText(searchCustomer.getCustomerID());
            lblCustomerName.setText(searchCustomer.getFirstName()+" "+searchCustomer.getLastName());
            lblCustomerAddress.setText(searchCustomer.getAddress());
            lblNIC.setText(searchCustomer.getNic());
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Customer not found!");
            alert.show();
        }
    }
}
