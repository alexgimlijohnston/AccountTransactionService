package com.service.dao.account;

import com.service.dao.DatabaseUtil;
import com.service.domain.Account;
import java.util.Optional;
import org.hibernate.Session;

public class AccountDAOImpl implements AccountDAO {

    public void createAccount(Account account) {
        DatabaseUtil.insertWithTransaction(account);
    }

    public Optional<Account> getAccountById(Integer id) {
        Session session = DatabaseUtil.getSessionFactory().openSession();
        return AccountRepository.getAccountById(session, id);
    }

}
