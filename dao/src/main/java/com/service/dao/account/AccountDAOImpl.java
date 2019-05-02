package com.service.dao.account;

import com.service.dao.DatabaseUtil;
import com.service.domain.Account;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class AccountDAOImpl implements AccountDAO {

    @Override
    public void createAccount(Account account) throws Exception {
        DatabaseUtil.insertObject(account);
    }

    @Override
    public Optional<Account> getAccountById(Integer id) {
        Class<Account> typeOfClass = Account.class;
        return DatabaseUtil.selectObject(typeOfClass, id);
    }

    @Override
    public Optional<List<Account>> getAccountsByCustomerId(Integer customerId) {
        return Optional.empty();
    }

}
