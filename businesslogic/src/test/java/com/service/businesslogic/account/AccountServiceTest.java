package com.service.businesslogic.account;

import com.service.dao.account.AccountDAO;
import com.service.dao.account.AccountDAOImpl;
import com.service.domain.Account;
import com.service.dto.AccountDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    private AccountService accountService;

    private AccountDAO accountDAO;

    private AccountMapper accountMapper;

    @Before
    public void setup() {
        accountDAO = mock(AccountDAOImpl.class);
        accountMapper = mock(AccountMapper.class);

        accountService = new AccountServiceImpl(accountDAO, accountMapper);
    }

    @Test
    public void getAccountById_validId_returnMappedAccountDto() {
        Integer accountId = 100;
        Account account = new Account();
        account.setAccountId(accountId);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(accountId);

        when(accountDAO.getAccountById(accountId)).thenReturn(Optional.of(account));
        when(accountMapper.mapAccountToAccountDTO(account)).thenReturn(accountDTO);

        Optional<AccountDTO> returned = accountService.getAccountById(accountId);

        assertTrue(returned.isPresent());
        assertEquals(returned.get(), accountDTO);
    }

    @Test
    public void getAccountById_invalidId_returnOptionalEmpty() {
        Integer accountId = 100;
        Account account = new Account();
        account.setAccountId(accountId);

        when(accountDAO.getAccountById(accountId)).thenReturn(Optional.empty());

        Optional<AccountDTO> returned = accountService.getAccountById(accountId);

        assertFalse(returned.isPresent());
    }

    @Test
    public void createAccount_validAccountDto_mapAndCreateAccount() throws Exception {
        Integer accountId = 100;
        Account account = new Account();
        account.setAccountId(accountId);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(accountId);

        when(accountMapper.mapAccountDTOToAccount(accountDTO)).thenReturn(account);

        accountService.createAccount(accountDTO);

        verify(accountDAO, times(1)).createAccount(account);
    }

}
