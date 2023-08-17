package bo.custom;

import bo.SuperBO;
import dto.ItemDTO;
import entity.Item;
import javafx.collections.ObservableList;

public interface ItemBO extends SuperBO {
    public boolean saveItem(ItemDTO itemDTO);

    public String getNextItemID();

    public ObservableList<ItemDTO> getAllItems();

    public ItemDTO getItemById(String itemID);

    public boolean updateItem(ItemDTO itemDTO);



}
