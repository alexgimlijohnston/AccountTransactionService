package com.service.businesslogic.account;

import com.service.dto.AccountDTO;
import java.util.Optional;

public interface AccountService {

    Optional<AccountDTO> getAccountById(Integer id);

    void createAccount(AccountDTO accountDTO);

}
