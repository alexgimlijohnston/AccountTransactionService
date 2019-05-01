package com.service.dao.account;

import com.service.dao.DatabaseUtil;
import com.service.domain.Account;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class AccountDAOImpl implements AccountDAO {

    @Override
    public void createAccount(Account account) throws Exception {
        AccountRepository.insertAccount(account);
    }

    @Override
    public Optional<Account> getAccountById(Integer id) {
        Session session = DatabaseUtil.getNewSession();
        return AccountRepository.getAccountById(session, id);
    }

    @Override
    public Optional<List<Account>> getAccountsByCustomerId(Integer customerId) {
//        Session session = DatabaseUtil.getNewSession();
        return Optional.empty();
    }

}
