package com.service.dao.customer;

import com.service.dao.DatabaseUtil;
import com.service.domain.Customer;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public void createCustomer(Customer customer) throws Exception {
        DatabaseUtil.insertObject(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        Class<Customer> typeOfClass = Customer.class;
        return DatabaseUtil.selectObject(typeOfClass, id);
    }

}