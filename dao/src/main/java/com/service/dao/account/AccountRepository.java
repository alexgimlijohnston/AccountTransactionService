package com.service.dao.account;

import com.service.dao.DatabaseUtil;
import com.service.domain.Account;
import org.hibernate.Session;
import java.util.Optional;

public class AccountRepository {

    public static Optional<Account> getAccountById(Session session, Integer id) {
        String query = String.format("from Account where accountId = %d", id);
        Class<Account> typeOfClass = Account.class;
        return DatabaseUtil.selectObject(session, query, typeOfClass);
    }

    public static void insertAccount(Account account) throws Exception {
        DatabaseUtil.insertObject(account);
    }


}
