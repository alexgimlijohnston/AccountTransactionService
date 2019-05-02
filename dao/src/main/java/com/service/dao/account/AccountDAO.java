package com.service.dao.account;

import com.service.domain.Account;
import java.util.Optional;

public interface AccountDAO {

   void createAccount(Account account) throws Exception;

   Optional<Account> getAccountById(Integer id);

}
