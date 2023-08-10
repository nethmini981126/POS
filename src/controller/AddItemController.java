package controller;

import bo.BOFactory;
import bo.custom.ItemBO;
import dto.ItemDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ITEM);

    public void btnAddItemOnAction(ActionEvent actionEvent) {
        ItemDTO itemDTO = new ItemDTO(
                txtItemID.getText(),
                txtItemName.getText(),
                txtBatchNo.getText(),
                Double.parseDouble(txtItemPrice.getText()),
                Double.parseDouble(txtItemQty.getText()),
                txtSupplier.getText(),
                Date.valueOf(LocalDate.now())
                );
        boolean b = itemBO.saveItem(itemDTO);
        System.out.println(b);
    }
}
