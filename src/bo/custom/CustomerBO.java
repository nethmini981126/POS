package bo.custom;


import bo.SuperBO;
import dto.CustomerDTO ;
import dto.ItemDTO;
import javafx.collections.ObservableList;

public interface CustomerBO extends SuperBO {
    public boolean saveCustomer(CustomerDTO customerDTO);

    public String getNextCustomerID();

    public ObservableList<CustomerDTO> getAllCustomers();
}
