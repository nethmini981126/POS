package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean save(Item item) {
        try{
            return CrudUtil.executeUpdate("INSERT INTO item VALUES(?,?,?,?,?,?,?)",
                    item.getItemID(),
                    item.getItemName(),
                    item.getBatchNumber(),
                    item.getPrice(),
                    item.getQty(),
                    item.getSupplier(),
                    item.getExpDate()
            );
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public String getLastItemId() {
        try{
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item ORDER BY itemID DESC LIMIT 1");
            if(resultSet.next()){
                return resultSet.getString("itemID");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return "I000";
    }

    @Override
    public ArrayList<Item> getAllItems() {

        ArrayList<Item> allItems = new ArrayList<>();
        try{
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item");
            while(resultSet.next()){
                allItems.add(new Item(
                        resultSet.getString("itemID"),
                        resultSet.getString("itemName"),
                        resultSet.getString("batchNumber"),
                        resultSet.getDouble("price"),
                        resultSet.getDouble("qty"),
                        resultSet.getString("supplier"),
                        resultSet.getDate("expireDate")
                ));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return allItems;
    }

    @Override
    public Item getItemById(String itemID) {
        try{
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item WHERE itemID=?",itemID);
            if(resultSet.next()){
                return new Item(
                        resultSet.getString("itemID"),
                        resultSet.getString("itemName"),
                        resultSet.getString("batchNumber"),
                        resultSet.getDouble("price"),
                        resultSet.getDouble("qty"),
                        resultSet.getString("supplier"),
                        resultSet.getDate("expireDate")
                );
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateItem(Item item) {
        try{
            return CrudUtil.executeUpdate("UPDATE item SET itemName=?, batchNumber=?, price=?, qty=?, supplier=?, expireDate=? WHERE itemID=?",
                    item.getItemName(),
                    item.getBatchNumber(),
                    item.getPrice(),
                    item.getQty(),
                    item.getSupplier(),
                    item.getExpDate(),
                    item.getItemID()
            );
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public HashMap<String, String> getItemNames() {
        HashMap<String, String> itemsAndNames = new HashMap<>();
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item");
            while(resultSet.next()){
                itemsAndNames.put(
                        resultSet.getString("itemID"),
                        resultSet.getString("itemName")
                );
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return itemsAndNames;
    }

    @Override
    public Item getItemByName(String itemName) {
        try{
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item WHERE itemName=?",itemName);
            if(resultSet.next()){
                return new Item(
                        resultSet.getString("itemID"),
                        resultSet.getString("itemName"),
                        resultSet.getString("batchNumber"),
                        resultSet.getDouble("price"),
                        resultSet.getDouble("qty"),
                        resultSet.getString("supplier"),
                        resultSet.getDate("expireDate")
                );
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateItemQty(String id, double qty) {
        try{
            return (CrudUtil.executeUpdate("UPDATE item SET qty=? WHERE itemID = ?",
                    qty,
                    id
            ));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
