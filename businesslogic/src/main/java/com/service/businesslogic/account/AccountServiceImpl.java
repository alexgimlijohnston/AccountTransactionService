package com.service.businesslogic.account;

import com.service.dao.account.AccountDAO;
import com.service.dao.account.AccountDAOImpl;
import com.service.domain.Account;
import com.service.dto.AccountDTO;

public class AccountServiceImpl implements AccountService {

    private AccountDAO accountDAO;

    public AccountServiceImpl() {
        this.accountDAO = new AccountDAOImpl();
    }

    public AccountDTO getAccountById(Integer id) {
        Account account = accountDAO.getAccountById(id);
        return mapAccountToAccountDTO(account);
    }

    private AccountDTO mapAccountToAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(account.getAccountId());
        accountDTO.setAccountHolderName(account.getAccountHolderName());
        return accountDTO;
    }

}
