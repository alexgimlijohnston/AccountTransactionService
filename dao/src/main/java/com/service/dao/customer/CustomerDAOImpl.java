package com.service.dao.customer;

import com.service.dao.DatabaseUtil;
import com.service.domain.Customer;
import org.hibernate.Session;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public void createCustomer(Customer customer) {
        CustomerRepository.insertCustomer(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        Session session = DatabaseUtil.getNewSession();
        return CustomerRepository.getCustomerById(session, id);
    }

}