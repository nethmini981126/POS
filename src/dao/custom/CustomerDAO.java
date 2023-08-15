package dao.custom;

import dao.SuperDAO;
import entity.Customer;

import java.util.ArrayList;

public interface CustomerDAO extends SuperDAO {
    public boolean save(Customer customer);

    public String getLastCustomerID();

    public ArrayList<Customer> getAllCustomers();


}
