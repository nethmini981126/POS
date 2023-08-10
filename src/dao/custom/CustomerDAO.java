package dao.custom;

import dao.SuperDAO;
import entity.Customer;

public interface CustomerDAO extends SuperDAO {
    public boolean save(Customer customer);
}
