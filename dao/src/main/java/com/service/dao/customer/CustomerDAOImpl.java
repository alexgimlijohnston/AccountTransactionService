package com.service.dao.customer;

import com.service.dao.DatabaseUtil;
import com.service.domain.Customer;
import org.hibernate.Session;
import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {

    public void createCustomer(Customer customer) {
        DatabaseUtil.insertWithTransaction(customer);
    }

    public Optional<Customer> getCustomerById(Integer id) {
        Session session = DatabaseUtil.getSessionFactory().openSession();
        List<Customer> customers = session.createQuery(String.format("from Customer where customerId = %d", id), Customer.class).list();
        return customers.isEmpty() ? Optional.empty() : Optional.of(customers.get(0));
    }

}