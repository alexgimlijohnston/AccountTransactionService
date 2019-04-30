package com.service.dao.account;

import com.service.domain.Account;
import org.hibernate.Session;
import java.util.List;
import java.util.Optional;

public class AccountRepository {

    public static Optional<Account> getAccountById(Session session, Integer id) {
        List<Account> accounts = session.createQuery(String.format("from Account where accountId = %d", id), Account.class).list();
        return accounts.isEmpty() ? Optional.empty() : Optional.of(accounts.get(0));
    }

}
