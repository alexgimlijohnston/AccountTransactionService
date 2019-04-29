package com.service.dao.account;

import com.service.dao.DatabaseUtil;
import com.service.domain.Account;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccountDAOImpl implements AccountDAO {

    public Optional<Account> getAccountById(Integer id) {
        Account acc = new Account(id, 2324.22);
        Transaction transaction = null;
        try (Session session = DatabaseUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.save(acc);
            transaction.commit();
            return Optional.ofNullable(session.createQuery("from Account where accountId = " + id, Account.class).list().get(0));

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return Optional.empty();
    }

}
