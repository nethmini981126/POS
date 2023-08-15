package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;
import entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(Customer customer) {
        try{
            return CrudUtil.executeUpdate("INSERT INTO customer VALUES(?,?,?,?,?,?)",
                    customer.getCustomerID(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getNic(),
                    customer.getAddress(),
                    customer.getContactNumber()
                    );
        }catch (SQLException | ClassNotFoundException throwables ){
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public String getLastCustomerID() {
        try{
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM customer ORDER BY customerID DESC LIMIT 1");
            if(resultSet.next()){
                return resultSet.getString("customerID");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return "C000";
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        try{
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM customer");
            while(resultSet.next()){
                allCustomers.add(new Customer(
                        resultSet.getString("customerID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("nic"),
                        resultSet.getString("address"),
                        resultSet.getString("contactNumber")
                ));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return allCustomers;
    }
}
