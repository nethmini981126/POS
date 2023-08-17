package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import dto.ItemDTO;
import entity.Customer;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class CustomerBoImpl implements CustomerBO {

    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
        //Create Customer entity
        Customer customer = new Customer(
                customerDTO.getCustomerID(),
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getNic(),
                customerDTO.getAddress(),
                customerDTO.getContactNumber()
        );
        //save customer via customerDAO
        return customerDAO.save(customer);
    }

    @Override
    public String getNextCustomerID() {
        String LastCustomerID = customerDAO.getLastCustomerID();
        int lastnumber = Integer.parseInt(LastCustomerID.substring(1));
        return String.format("C%03d",++lastnumber);
    }

    @Override
    public ObservableList<CustomerDTO> getAllCustomers() {

        ArrayList<Customer> allCustomers = customerDAO.getAllCustomers();
        ObservableList<CustomerDTO> allCustomersForTable  = FXCollections.observableArrayList();
        for (Customer c : allCustomers){
            allCustomersForTable.add(new CustomerDTO(
                    c.getCustomerID(),
                    c.getFirstName(),
                    c.getLastName(),
                    c.getNic(),
                    c.getAddress(),
                    c.getContactNumber()
            ));
        }
        return allCustomersForTable;
    }

    @Override
    public CustomerDTO getCustomerById(String customerID) {
        Customer customerByID = customerDAO.getCustomerById(customerID);
        if(customerByID != null){
            return new CustomerDTO(
                    customerByID.getCustomerID(),
                    customerByID.getFirstName(),
                    customerByID.getLastName(),
                    customerByID.getNic(),
                    customerByID.getAddress(),
                    customerByID.getContactNumber()
            );
        }
        return null;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) {
        return customerDAO.updateCustomer(new Customer(
                customerDTO.getCustomerID(),
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getNic(),
                customerDTO.getAddress(),
                customerDTO.getContactNumber()
        ));
    }
}
