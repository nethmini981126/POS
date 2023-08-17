package dao.custom;

import dao.SuperDAO;
import entity.Customer;
import entity.Item;

import java.util.ArrayList;

public interface CustomerDAO extends SuperDAO {
    public boolean save(Customer customer);

    public String getLastCustomerID();

    public ArrayList<Customer> getAllCustomers();

    public Customer getCustomerById(String customerID);

    public boolean updateCustomer(Customer customer);

}
