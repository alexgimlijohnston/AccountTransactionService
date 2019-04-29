package com.service.services.resources;

import com.service.businesslogic.account.AccountService;
import com.service.businesslogic.account.AccountServiceImpl;
import com.service.dto.AccountDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    private AccountService accountService;

    public AccountResource() {
        this.accountService = new AccountServiceImpl();
    }

    @GET
    @Path("/{id}")
    public AccountDTO getAccount(@PathParam("id") Integer id) {
        Optional<AccountDTO> accountDTO = accountService.getAccountById(id);
        if(accountDTO.isPresent()) {
            return accountDTO.get();
        }
        throw new NotFoundException("No Account Exists");
    }
}