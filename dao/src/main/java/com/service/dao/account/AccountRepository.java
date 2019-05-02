package com.service.dao.account;

import com.service.dao.DatabaseUtil;
import com.service.domain.Account;
import java.util.Optional;

public class AccountRepository {

    public static Optional<Account> getAccountById(Integer id) {
        Class<Account> typeOfClass = Account.class;
        return DatabaseUtil.selectObject(typeOfClass, id);
    }

    public static void insertAccount(Account account) throws Exception {
        DatabaseUtil.insertObject(account);
    }


}
