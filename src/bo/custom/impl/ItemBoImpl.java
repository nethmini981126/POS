package bo.custom.impl;

import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dto.ItemDTO;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ItemBoImpl implements ItemBO {

    ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean saveItem(ItemDTO itemDTO) {
        // Create Item entity
       Item item = new Item(
               itemDTO.getItemID(),
               itemDTO.getItemName(),
               itemDTO.getBatchNumber(),
               itemDTO.getPrice(),
               itemDTO.getQty(),
               itemDTO.getSupplier(),
               itemDTO.getExpDate()
       );
       //save item via ItemDAO
        return itemDAO.save(item);

    }

    @Override
    public String getNextItemID() {
        //create nextItem ID
        String LastItemID = itemDAO.getLastItemId();
        int lastnumber = Integer.parseInt(LastItemID.substring(1));
        return String.format("I%03d",++lastnumber);
    }

    @Override
    public ObservableList<ItemDTO> getAllItems() {

        ArrayList<Item> allItems = itemDAO.getAllItems();
        ObservableList<ItemDTO> allItemsForTable  = FXCollections.observableArrayList();
        for (Item a : allItems){
            allItemsForTable.add(new ItemDTO(
                    a.getItemID(),
                    a.getItemName(),
                    a.getBatchNumber(),
                    a.getPrice(),
                    a.getQty(),
                    a.getSupplier(),
                    a.getExpDate()
            ));
        }
        return allItemsForTable;
    }

    @Override
    public ItemDTO getItemById(String itemID) {
        Item itemByID = itemDAO.getItemById(itemID);
        if(itemByID != null){
            return new ItemDTO(
                    itemByID.getItemID(),
                    itemByID.getItemName(),
                    itemByID.getBatchNumber(),
                    itemByID.getPrice(),
                    itemByID.getQty(),
                    itemByID.getSupplier(),
                    itemByID.getExpDate()
            );
        }
        return null;
    }
}
