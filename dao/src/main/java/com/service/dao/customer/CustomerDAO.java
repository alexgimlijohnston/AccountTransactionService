package com.service.dao.customer;

import com.service.domain.Customer;
import java.util.Optional;

public interface CustomerDAO {

    void createCustomer(Customer customer) throws Exception;

    Optional<Customer> getCustomerById(Integer id);

}
