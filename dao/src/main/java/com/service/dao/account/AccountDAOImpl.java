package com.service.dao.account;

import com.service.dao.DatabaseUtil;
import com.service.domain.Account;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccountDAOImpl implements AccountDAO {

    public void createAccount(Account account) {
        Transaction transaction = null;
        try {
            Session session = DatabaseUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public Optional<Account> getAccountById(Integer id) {
        Session session = DatabaseUtil.getSessionFactory().openSession();
        List<Account> accounts = session.createQuery(String.format("from Account where accountId = %d", id), Account.class).list();
        return accounts.isEmpty() ? Optional.empty() : Optional.of(accounts.get(0));
    }

}
