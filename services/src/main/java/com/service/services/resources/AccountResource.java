package com.service.services.resources;

import com.service.businesslogic.account.AccountService;
import com.service.businesslogic.account.AccountServiceImpl;
import com.service.dto.AccountDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    private AccountService accountService;

    public AccountResource() {
        this.accountService = new AccountServiceImpl();
    }

    @GET
    @Path("/{id}")
    public AccountDTO sayHello(@PathParam("id") Integer id) {
        return accountService.getAccountById(id);
    }
}