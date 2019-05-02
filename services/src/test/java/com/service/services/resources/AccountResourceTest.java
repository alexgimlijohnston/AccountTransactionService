package com.service.services.resources;

import com.service.businesslogic.account.AccountService;
import com.service.businesslogic.account.AccountServiceImpl;
import com.service.dto.AccountDTO;
import org.junit.Before;
import org.junit.Test;
import javax.ws.rs.core.Response;
import java.util.Optional;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class AccountResourceTest {

    private AccountService accountService;

    private AccountResource accountResource;

    @Before
    public void setup() {
        accountService = mock(AccountServiceImpl.class);
        accountResource = new AccountResource(accountService);
    }

    @Test
    public void getAccount_accountThatExists_returnAccountAndOkStatusCode() {
        Integer accountId = 100;
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(accountId);

        when(accountService.getAccountById(accountId)).thenReturn(Optional.of(accountDTO));

        Response response = accountResource.getAccount(accountId);

        assertEquals(response.getStatus(),200);
        assertEquals(response.getEntity(),accountDTO);
    }

    @Test
    public void getAccount_accountThatDoesNotExist_returnNotFoundStatusCode() {
        Integer accountId = 100;
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(accountId);

        when(accountService.getAccountById(accountId)).thenReturn(Optional.empty());

        Response response = accountResource.getAccount(accountId);

        assertEquals(response.getStatus(),404);
        assertEquals(response.getEntity(),String.format("Unable to find account with id %d", accountId));
    }

    @Test
    public void createAccount_accountWasAbleToBeCreated_returnOkStatusCode() {
        Integer accountId = 100;
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(accountId);
        accountDTO.setSortCode("40-90-23");

        Response response = accountResource.createAccount(accountDTO);

        assertEquals(response.getStatus(),200);
        assertEquals(response.getEntity(),String.format("Account with id %d was successfully created", accountDTO.getAccountId()));
    }

    @Test
    public void createAccount_accountWasNotAbleToBeCreated_returnBadRequestStatusCode() throws Exception {
        Integer accountId = 100;
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(accountId);

        doThrow(new Exception()).when(accountService).createAccount(accountDTO);

        Response response = accountResource.createAccount(accountDTO);

        assertEquals(response.getStatus(),400);
        assertEquals(response.getEntity(),String.format("Account with id %d could not be created", accountDTO.getAccountId()));
    }

}
