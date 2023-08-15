package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
}
