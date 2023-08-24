package bo.custom;

import bo.SuperBO;
import dto.ItemDTO;
import entity.Item;
import javafx.collections.ObservableList;

import java.util.HashMap;

public interface ItemBO extends SuperBO {
    public boolean saveItem(ItemDTO itemDTO);

    public String getNextItemID();

    public ObservableList<ItemDTO> getAllItems();

    public ItemDTO getItemById(String itemID);

    public boolean updateItem(ItemDTO itemDTO);

    public HashMap<String, String> getItemNamesAndIDs();

    public ItemDTO getItemByName(String itemName);

    public boolean updateItemQty(String id, double qty);



}
