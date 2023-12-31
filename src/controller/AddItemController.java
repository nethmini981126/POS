package controller;

import bo.BOFactory;
import bo.custom.ItemBO;
import dto.ItemDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.sql.Date;
import java.time.LocalDate;

public class AddItemController {
    public TextField txtItemID;
    public TextField txtItemName;
    public TextField txtItemPrice;
    public TextField txtBatchNo;
    public TextField txtSupplier;
    public TextField txtItemQty;
    public DatePicker pickerExpDate;
    public Label lblOnlyNumPrice;
    public Label lblOnlyNumQty;
    public TableColumn colItemId;
    public TableColumn colItemName;
    public TableColumn colBatchNo;
    public TableColumn colItemPrice;
    public TableColumn colQty;
    public TableColumn colSupplier;
    public TableColumn colExpDate;
    public TableView tblItems;
    public Button btnADDItem;
    public Button btnDeleteItem;
    private int selectedIndex = -1;
    private ObservableList<ItemDTO> allItems;
    private boolean isEdit = false;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ITEM);

    public void initialize(){
        generateAndSetNextID();
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colBatchNo.setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        colExpDate.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        setDataToTable();

        //set listener to the table
        tblItems.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectedIndex = (int) newValue;
        });
    }

    private void generateAndSetNextID() {
        txtItemID.setText(itemBO.getNextItemID());
    }

    private void setDataToTable() {
        allItems = itemBO.getAllItems();
        tblItems.setItems(allItems);
    }

    public void btnAddItemOnAction(ActionEvent actionEvent) {
        boolean allFieldsFilled = true;

        if(!isEdit){
            if(
                    txtItemID.getText().isEmpty() ||
                            txtItemName.getText().isEmpty() ||
                            txtItemPrice.getText().isEmpty() ||
                            txtItemQty.getText().isEmpty() ||
                            txtBatchNo.getText().isEmpty() ||
                            txtSupplier.getText().isEmpty() ||
                            pickerExpDate.getEditor().getText().isEmpty()
            ){
                allFieldsFilled = false;
            }

            if(allFieldsFilled){
                ItemDTO itemDTO = new ItemDTO(
                        txtItemID.getText(),
                        txtItemName.getText(),
                        txtBatchNo.getText(),
                        Double.parseDouble(txtItemPrice.getText()),
                        Double.parseDouble(txtItemQty.getText()),
                        txtSupplier.getText(),
                        Date.valueOf(LocalDate.now())
                );
                //save item
                itemBO.saveItem(itemDTO);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Item added successfully!");
                alert.show();
                //Show item on table
                setDataToTable();
                //clear fields
                clearField();
                //generate next ID
                generateAndSetNextID();

            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Fill all fields!");
                alert.show();
            }
        }else{
            //updata item
            boolean updated = itemBO.updateItem(new ItemDTO(
                    txtItemID.getText(),
                    txtItemName.getText(),
                    txtBatchNo.getText(),
                    Double.parseDouble(txtItemPrice.getText()),
                    Double.parseDouble(txtItemQty.getText()),
                    txtSupplier.getText(),
                    Date.valueOf(LocalDate.now())
            ));
            if(updated){
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Successfully Updated!");
                alert.show();
                clearField();
                initialize();
                btnADDItem.setText("ADD");
                btnADDItem.setStyle("-fx-background-color:  #996515");
                isEdit=false;
            }
        }
    }

    private void clearField() {
        txtItemID.clear();
        txtItemName.clear();
        txtItemPrice.clear();
        txtItemQty.clear();
        txtBatchNo.clear();
        txtSupplier.clear();
        pickerExpDate.getEditor().clear();
        generateAndSetNextID();
    }

    public void txtPriceOnKeyRelease(KeyEvent keyEvent) {
        // Validation for price field
        if(txtItemPrice.getText().matches("\\b\\d+(?:\\.\\d{1,2})?\\b")){
            txtItemPrice.setStyle("-fx-border-color:  #F0E68C");
            txtItemPrice.setStyle("-fx-background-color: #F0E68C");
            lblOnlyNumPrice.setVisible(false);
        }else {
            txtItemPrice.setStyle("-fx-border-color: #dd0808");
            lblOnlyNumPrice.setVisible(true);

        }
    }

    public void txtQtyOnKeyRelease(KeyEvent keyEvent) {
        // Validation for quantity field
        if(txtItemQty.getText().matches("\\b\\d+(?:\\.\\d{1,2})?\\b")){
            txtItemQty.setStyle("-fx-border-color:  #F0E68C");
            txtItemQty.setStyle("-fx-background-color: #F0E68C");
            lblOnlyNumQty.setVisible(false);
        }else {
            txtItemQty.setStyle("-fx-border-color: #dd0808");
            lblOnlyNumQty.setVisible(true);

        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearField();
        generateAndSetNextID();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    private void loadItemDataToFields(ItemDTO selectedItem){
        ItemDTO selectedItemDetails = itemBO.getItemById(selectedItem.getItemID());
        txtItemID.setText(selectedItemDetails.getItemID());
        txtItemName.setText(selectedItemDetails.getItemName());
        txtItemPrice.setText(String.format("%.2f",selectedItemDetails.getPrice()));
        txtItemQty.setText(String.format("%.2f",selectedItemDetails.getQty()));
        txtSupplier.setText(selectedItemDetails.getSupplier());
        txtBatchNo.setText(selectedItemDetails.getBatchNumber());
        pickerExpDate.getEditor().setText(String.valueOf(selectedItemDetails.getExpDate()));
    }

    public void btnEditOnAction(ActionEvent actionEvent) {
        if(selectedIndex != -1){
            loadItemDataToFields(allItems.get(selectedIndex));
            btnADDItem.setText("UPDATE");
            btnADDItem.setStyle("-fx-background-color: #c0392b");
            isEdit = true;
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please select Item first..");
            alert.show();
        }
    }
}
