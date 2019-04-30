package com.service.services.resources;

import com.service.businesslogic.transaction.TransactionService;
import com.service.businesslogic.transaction.TransactionServiceImpl;
import com.service.dto.TransactionDTO;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
public class TransactionResource {

    private TransactionService transactionService;

    public TransactionResource() {
        this.transactionService = new TransactionServiceImpl();
    }

    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/transfer")
    public void createAccount(@NotNull TransactionDTO transactionDTO) {
        transactionService.transfer(transactionDTO);
    }

}