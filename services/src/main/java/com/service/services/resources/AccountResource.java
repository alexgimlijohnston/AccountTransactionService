package com.service.services.resources;

import com.service.businesslogic.account.AccountService;
import com.service.businesslogic.account.AccountServiceImpl;
import com.service.dto.AccountDTO;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    private AccountService accountService;

    public AccountResource() {
        this.accountService = new AccountServiceImpl();
    }

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    @Path("/{id}")
    public Response getAccount(@PathParam("id") Integer id) {
        Optional<AccountDTO> accountDTO = accountService.getAccountById(id);
        return accountDTO.isPresent()
                ? Response.ok().entity(accountDTO.get()).build()
                : Response.status(404).entity(String.format("Unable to find account with id &d", id)).build();
    }

    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/create")
    public Response createAccount(@NotNull AccountDTO accountDTO) {
        try {
            accountService.createAccount(accountDTO);
            return Response.ok().entity(String.format("Account with id %d was successfully created", accountDTO.getAccountId())).build();
        } catch (Exception e) {
            return Response.status(400).entity(String.format("Account with id %d could not be created", accountDTO.getAccountId())).build();
        }
    }

}