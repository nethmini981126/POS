package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import dto.CustomerDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AddCustomerController {
    public TextField txtCustomerID;
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtNIC;
    public TableView tblCustomers;
    public TableColumn colCustomerID;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colNIC;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.CUSTOMER);

    public void initialize(){
        generateAndSetCustomerNextID();
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        setDataToCustomerTable();
    }

    private void setDataToCustomerTable() {
        ObservableList<CustomerDTO> allCustomers = customerBO.getAllCustomers();
        tblCustomers.setItems(allCustomers);
    }

    private void generateAndSetCustomerNextID() {
        txtCustomerID.setText(customerBO.getNextCustomerID());
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        boolean allFieldsFilled = true;
        if (
                txtCustomerID.getText().isEmpty()||
                txtFirstName.getText().isEmpty()||
                txtLastName.getText().isEmpty()||
                txtNIC.getText().isEmpty()||
                txtAddress.getText().isEmpty()||
                txtContact.getText().isEmpty()
        ){
            allFieldsFilled = false;
        }

        if(allFieldsFilled){
            CustomerDTO customerDTO = new CustomerDTO(
                    txtCustomerID.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtNIC.getText(),
                    txtAddress.getText(),
                    txtContact.getText()
            );
            //save customer
            customerBO.saveCustomer(customerDTO);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Customer added succefully!");
            alert.show();
            //Show customer on table
            setDataToCustomerTable();
            // clear fields
            clearFields();
            //generate and set nest ID
            generateAndSetCustomerNextID();

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Fill all fields!");
            alert.show();

        }

    }

    private void clearFields() {
        txtCustomerID.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtNIC.clear();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
        generateAndSetCustomerNextID();
    }
}
