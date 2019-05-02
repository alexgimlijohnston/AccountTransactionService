package com.service.dao.customer;

import com.service.dao.DatabaseUtil;
import com.service.domain.Customer;
import org.hibernate.Session;
import java.util.Optional;

public class CustomerRepository {

    public static Optional<Customer> getCustomerById(Integer id) {
        String query = String.format("from Customer where customerId = %d", id);
        Class<Customer> typeOfClass = Customer.class;
        return DatabaseUtil.selectObject(typeOfClass, id);
    }

    public static void insertCustomer(Customer customer) throws Exception {
        DatabaseUtil.insertObject(customer);
    }


}
