package com.service.businesslogic.account;

import com.service.dao.account.AccountDAO;
import com.service.dao.account.AccountDAOImpl;
import com.service.domain.Account;
import com.service.dto.AccountDTO;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private AccountDAO accountDAO;

    public AccountServiceImpl() {
        this.accountDAO = new AccountDAOImpl();
    }

    public Optional<AccountDTO> getAccountById(Integer id) {
        Optional<Account> account = accountDAO.getAccountById(id);
        return account.map(this::mapAccountToAccountDTO);
    }

    private AccountDTO mapAccountToAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(account.getAccountId());
        accountDTO.setBalance(account.getBalance());
        return accountDTO;
    }

}
