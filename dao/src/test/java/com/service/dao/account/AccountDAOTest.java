package com.service.dao.account;

import com.service.dao.DatabaseUtil;
import com.service.domain.Account;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DatabaseUtil.class,AccountRepository.class})
public class AccountDAOTest {

    private AccountDAO accountDAO;

    @Before
    public void initMocks() {
        accountDAO = new AccountDAOImpl();

        mockStatic(AccountRepository.class);
        mockStatic(DatabaseUtil.class);
    }

    @Test
    public void createAccount_account_insertAccountIntoDb() throws Exception {
        Integer id = 2000;
        String sortCode = "10-12-45";
        Account account = new Account(id, sortCode);

        accountDAO.createAccount(account);

        verifyStatic(VerificationModeFactory.times(1));
    }

    @Test
    public void getAccountById_validId_returnAccount() {
        Integer id = 2000;
        String sortCode = "10-12-45";
        Session session = mock(Session.class);
        Account account = new Account(id, sortCode);

        when(DatabaseUtil.getNewSession()).thenReturn(session);
        when(AccountRepository.getAccountById(session, id)).thenReturn(Optional.of(account));

        Optional<Account> result = accountDAO.getAccountById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getAccountId());
        assertEquals(sortCode, result.get().getSortCode());
    }

    @Test
    public void getAccountById_invalidId_returnOptionalEmpty() {
        Integer id = 2000;
        Session session = mock(Session.class);

        when(DatabaseUtil.getNewSession()).thenReturn(session);
        when(AccountRepository.getAccountById(session, id)).thenReturn(Optional.empty());

        Optional<Account> result = accountDAO.getAccountById(id);

        assertFalse(result.isPresent());
    }

}
