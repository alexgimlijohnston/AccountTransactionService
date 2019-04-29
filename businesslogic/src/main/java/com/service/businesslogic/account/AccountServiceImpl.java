package com.service.businesslogic.account;

import com.service.dao.account.AccountDAO;
import com.service.dao.account.AccountDAOImpl;
import com.service.domain.Account;
import com.service.dto.AccountDTO;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private AccountDAO accountDAO;

    private AccountMapper accountMapper;

    public AccountServiceImpl() {
        this.accountDAO = new AccountDAOImpl();
        this.accountMapper = new AccountMapper();
    }

    public Optional<AccountDTO> getAccountById(Integer id) {
        Optional<Account> account = accountDAO.getAccountById(id);
        return account.map(account1 -> accountMapper.mapAccountToAccountDTO(account1));
    }

    public void createAccount(AccountDTO accountDTO) {
        Account account = accountMapper.mapAccountDTOToAccount(accountDTO);
        accountDAO.createAccount(account);
    }



}
