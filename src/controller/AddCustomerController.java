package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import dto.CustomerDTO;
import dto.ItemDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.time.LocalDate;

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
    public Button btnADD;
    private int selectedIndex = -1;
    private ObservableList<CustomerDTO> allCustomers;
    private boolean isEdit = false;

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

        //set listener to the table
        tblCustomers.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectedIndex = (int) newValue;
        });
    }

    private void generateAndSetCustomerNextID() {
        txtCustomerID.setText(customerBO.getNextCustomerID());
    }

    private void setDataToCustomerTable() {
        allCustomers = customerBO.getAllCustomers();
        tblCustomers.setItems(allCustomers);
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        boolean allFieldsFilled = true;

        if(!isEdit){
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
        }else {
            //updata item
            boolean updated = customerBO.updateCustomer(new CustomerDTO(
                    txtCustomerID.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtNIC.getText(),
                    txtAddress.getText(),
                    txtContact.getText()
            ));
            if (updated) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Updated!");
                alert.show();
                clearFields();
                initialize();
                btnADD.setText("ADD");
                btnADD.setStyle("-fx-background-color:  #996515");
                isEdit = false;
            }
        }
    }

    private void clearFields() {
        txtCustomerID.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtNIC.clear();
        generateAndSetCustomerNextID();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
        generateAndSetCustomerNextID();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    private void loadCustomerDataToFields(CustomerDTO selectedCustomer){
        CustomerDTO selectedCustomerDetails = customerBO.getCustomerById(selectedCustomer.getCustomerID());
        txtCustomerID.setText(selectedCustomerDetails.getCustomerID());
        txtFirstName.setText(selectedCustomerDetails.getFirstName());
        txtLastName.setText(selectedCustomerDetails.getLastName());
        txtNIC.setText(selectedCustomerDetails.getNic());
        txtAddress.setText(selectedCustomerDetails.getAddress());
        txtContact.setText(selectedCustomerDetails.getContactNumber());
    }

    public void btnEditOnAction(ActionEvent actionEvent) {
        if(selectedIndex != -1){
            loadCustomerDataToFields(allCustomers.get(selectedIndex));
            btnADD.setText("UPDATE");
            btnADD.setStyle("-fx-background-color: #c0392b");
            isEdit = true;
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please select Item first..");
            alert.show();
        }
    }

}
