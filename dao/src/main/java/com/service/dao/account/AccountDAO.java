package com.service.dao.account;

import com.service.domain.Account;
import java.util.Optional;

public interface AccountDAO {

   Optional<Account> getAccountById(Integer id);


}
