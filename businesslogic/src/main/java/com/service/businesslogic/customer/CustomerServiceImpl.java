package com.service.businesslogic.customer;

import com.service.dao.customer.CustomerDAO;
import com.service.dao.customer.CustomerDAOImpl;
import com.service.domain.Customer;
import com.service.dto.CustomerDTO;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;

    private CustomerMapper customerMapper;

    public CustomerServiceImpl() {
        this.customerDAO = new CustomerDAOImpl();
        this.customerMapper = new CustomerMapper();
    }

    public CustomerServiceImpl(CustomerDAO customerDAO, CustomerMapper customerMapper) {
        this.customerDAO = customerDAO;
        this.customerMapper = customerMapper;
    }

    public Optional<CustomerDTO> getCustomerById(Integer id) {
        Optional<Customer> customer = customerDAO.getCustomerById(id);
        return customer.map(customer1 -> customerMapper.mapCustomerToCustomerDTO(customer1));
    }

    public void createCustomer(CustomerDTO customerDTO) throws Exception {
        Customer customer = customerMapper.mapCustomerDTOToCustomer(customerDTO);
        customerDAO.createCustomer(customer);
    }

}
