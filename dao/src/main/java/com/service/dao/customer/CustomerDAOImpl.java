package com.service.dao.customer;

import com.service.dao.DatabaseUtil;
import com.service.domain.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {

    public void createCustomer(Customer customer) {
        Transaction transaction = null;
        try {
            Session session = DatabaseUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public Optional<Customer> getCustomerById(Integer id) {
        Session session = DatabaseUtil.getSessionFactory().openSession();
        List<Customer> customers = session.createQuery(String.format("from Customer where customerId = %d", id), Customer.class).list();
        return customers.isEmpty() ? Optional.empty() : Optional.of(customers.get(0));
    }

}