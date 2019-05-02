package com.service.services.resources;

import com.service.businesslogic.transaction.TransactionService;
import com.service.businesslogic.transaction.TransactionServiceImpl;
import com.service.common.exceptions.AccountDoesNotExistException;
import com.service.common.exceptions.InvalidCurrencyConversionException;
import com.service.common.exceptions.InvalidFundsException;
import com.service.dto.TransactionDTO;
import com.service.services.validation.ValidTransactionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
public class TransactionResource {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionResource.class);

    private TransactionService transactionService;

    public TransactionResource() {
        this.transactionService = new TransactionServiceImpl();
    }

    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/")
    public Response transfer(@NotNull @ValidTransactionDTO TransactionDTO transactionDTO) {
        try {
            String message = String.format("Transferred %f%s from account %d to account %d",
                    transactionDTO.getAmount(), transactionDTO.getCurrency().getName(), transactionDTO.getSenderAccountId(), transactionDTO.getReceiverAccountId());
            LOG.info(message);
            transactionService.transfer(transactionDTO);
            return Response.ok().entity(message).build();
        } catch (AccountDoesNotExistException e) {
            LOG.warn(e.getMessage());
            return Response.status(404).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOG.info(e.getMessage());
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

}