package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import bo.custom.OrderBO;
import bo.custom.OrderDetailsBO;
import com.sun.org.apache.xpath.internal.operations.Or;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDetailsDTO;
import entity.OrderItems;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import java.util.HashMap;

public class DashboardController {
    public Label lblCustomerName;
    public Label lblCustomerAddress;
    public Label lblNIC;
    public TextField txtContactNumber;
    public Label lblCustomerID;
    public Button btnFind;
    public TextField txtDescription;
    public TextField txtRequestAmount;
    public TextField txtDiscount;
    private final CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.CUSTOMER);
    private final ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ITEM);
    private final OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ORDER);
    private final OrderDetailsBO orderDetailsBO = (OrderDetailsBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ORDERDETAILS);
    public ComboBox<String > cmbItemSelector;
    public Label lblOrderID1;
    public Label lblQty;
    public Label lblUnitPrice;
    public Label lblDiscount;
    public Label lblItemTotal;
    public Button btnAddCart;
    public TextField txtItemID;
    public TableView<OrderItems> tblallitems;
    public TableColumn<OrderItems, String> colItemCode;
    public TableColumn<OrderItems, Double> colQty;
    public TableColumn<OrderItems, Double> colUnitPrice;
    public TableColumn<OrderItems, Double> colItemTotal;
    public TableColumn<OrderItems, Double> colDiscount;
    public Button btnRemove;
    public Label lblSubTotal;
    public Label lblTotal;
    private ObservableList<OrderItems> allItems;
    private int selectedIndex = -1;
    private Double sub = 0.0;
    public void initialize(){

        setItemNames();
        allItems = FXCollections.observableArrayList(); // Initialize the allItems list

        //Getting next orderID
        lblOrderID1.setText(orderBO.generateNextOrderID());
        //table
        colItemCode.setCellValueFactory(new PropertyValueFactory<OrderItems, String>("itemID"));
        colQty.setCellValueFactory(new PropertyValueFactory<OrderItems, Double>("RequestQty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<OrderItems, Double>("unitPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<OrderItems, Double>("discount"));
        colItemTotal.setCellValueFactory(new PropertyValueFactory<OrderItems, Double>("itemTotal"));

        tblallitems.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectedIndex = (int) newValue;
        });
    }

    private void setItemNames() {
        HashMap<String, String> itemNamesAndIDs = itemBO.getItemNamesAndIDs();
        itemNamesAndIDs.forEach((key,value) -> {
            cmbItemSelector.getItems().add(value);
        });

    }

    public void btnFindOnAction(ActionEvent actionEvent) {
        //Check the customer from contact number
        CustomerDTO searchedCustomer = customerBO.getCustomerByContactNumber(txtContactNumber.getText());
        if(searchedCustomer != null){
            lblCustomerID.setText(searchedCustomer.getCustomerID());
            lblCustomerName.setText(searchedCustomer.getFirstName()+" "+searchedCustomer.getLastName());
            lblCustomerAddress.setText(searchedCustomer.getAddress());
            lblNIC.setText(searchedCustomer.getNic());
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Customer not found!");
            alert.show();
        }
    }

    public void showQtyAndUnitPrice(ActionEvent actionEvent) {
        ItemDTO selectedItem = itemBO.getItemByName(cmbItemSelector.getValue());
        if(selectedItem != null){
            lblQty.setText(String.format("%.2f",selectedItem.getQty()));
            lblUnitPrice.setText(String.format("%.2f",selectedItem.getPrice()));
            txtItemID.setText(selectedItem.getItemID());

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Item does not exist!");
            alert.show();
        }
    }

    public void CalculateItemTotal(KeyEvent keyEvent) {
        Double itemQty = Double.valueOf(txtRequestAmount.getText());
        Double itemUnitPrice = Double.valueOf(lblUnitPrice.getText());
        Double itemDiscount = Double.valueOf(txtDiscount.getText());

        Double itemTotalWithoutDiscount = itemUnitPrice*itemQty;
        Double Discount = itemUnitPrice*itemQty*itemDiscount/100;
        Double itemFinalTotal = itemTotalWithoutDiscount - Discount;
        lblItemTotal.setText(String.valueOf(itemFinalTotal));
    }

    public void AddToCartOnAction(ActionEvent actionEvent) {
        boolean allFieldsFilled = true;

        if(
                    txtRequestAmount.getText().isEmpty() ||
                    txtDiscount.getText().isEmpty()
            ){
                allFieldsFilled = false;
            }

            if(allFieldsFilled) {
                OrderItems orderItems = new OrderItems(
                        lblOrderID1.getText(),
                        txtItemID.getText(),
                        Double.parseDouble(txtRequestAmount.getText()),
                        Double.parseDouble(lblUnitPrice.getText()),
                        Double.parseDouble(txtDiscount.getText()),
                        Double.parseDouble(lblItemTotal.getText())
                        );

                allItems.add(orderItems);
                tblallitems.setItems(allItems);

                Double itemTotal = Double.valueOf(orderItems.getItemTotal());
                sub = sub + itemTotal;
                lblSubTotal.setText(String.valueOf(sub));
                lblTotal.setText(String.valueOf(sub));

                Double itemQty = Double.valueOf(lblQty.getText());
                Double getItemQty =  Double.valueOf(orderItems.getRequestQty());
                Double remainingQty = itemQty - getItemQty;
                String itemCode = txtItemID.getText();
                lblQty.setText(String.valueOf(remainingQty));

                //Update qty
                itemBO.updateItemQty(itemCode, remainingQty);

                //clear fields
                txtItemID.clear();
                txtRequestAmount.clear();
                txtDiscount.clear();
                lblItemTotal.setText("00.0");
            }
    }

    public void itemDetailsClearOnAction(ActionEvent actionEvent) {
        txtRequestAmount.clear();
        txtDiscount.clear();
        lblItemTotal.setText("0.00");
    }

    public void ItemRemoveOnAction(ActionEvent actionEvent) {
        OrderItems removedItem = allItems.get(selectedIndex);
        Double removedItemTotal = Double.valueOf(removedItem.getItemTotal());
        Double itemQty = Double.valueOf(lblQty.getText());
        Double removedItemQty = Double.valueOf(removedItem.getRequestQty());
        String removedItemID = removedItem.getItemID();
        sub = sub - removedItemTotal;

        if (selectedIndex != -1) {
            allItems.remove(selectedIndex);
            tblallitems.setItems(allItems);

            lblSubTotal.setText(String.valueOf(sub));
            lblTotal.setText(String.valueOf(sub));
            lblQty.setText(String.valueOf(itemQty+removedItemQty));
            itemBO.updateItemQty(removedItemID, (itemQty+removedItemQty));


        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an item to remove.");
            alert.show();
        }
    }
}
