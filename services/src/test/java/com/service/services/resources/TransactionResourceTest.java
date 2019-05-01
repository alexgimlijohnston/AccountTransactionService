package com.service.services.resources;

import com.service.businesslogic.transaction.TransactionService;
import com.service.common.enums.Currency;
import com.service.common.exceptions.AccountDoesNotExistException;
import com.service.common.exceptions.InvalidFundsException;
import com.service.dto.TransactionDTO;
import org.junit.Before;
import org.junit.Test;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class TransactionResourceTest {

    private TransactionService transactionService;

    private TransactionResource transactionResource;

    @Before
    public void setup() {
        transactionService = mock(TransactionService.class);
        transactionResource = new TransactionResource(transactionService);
    }

    @Test
    public void transfer_validTransactionDto_amountIsTransferredAndReturnOkStatus() {
        Integer senderAccountId = 100;
        Integer receiverAccountId = 200;
        BigDecimal amount = new BigDecimal(50);
        Currency currency = Currency.GBP;

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setSenderAccountId(senderAccountId);
        transactionDTO.setReceiverAccountId(receiverAccountId);
        transactionDTO.setAmount(amount);
        transactionDTO.setCurrency(currency);

        Response response = transactionResource.transfer(transactionDTO);

        assertEquals(response.getStatus(),200);
        assertEquals(response.getEntity(),String.format("Transferred %f%s from account %d to account %d",
                transactionDTO.getAmount(), transactionDTO.getCurrency().getName(), transactionDTO.getSenderAccountId(), transactionDTO.getReceiverAccountId()));
    }

    @Test
    public void transfer_transactionWithInvalidAccount_returnNotFoundStatus() throws Exception {
        Integer senderAccountId = 100;

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setSenderAccountId(senderAccountId);

        String error = String.format("Account %d does not exist", senderAccountId);

        doThrow(new AccountDoesNotExistException(error)).when(transactionService).transfer(transactionDTO);

        Response response = transactionResource.transfer(transactionDTO);

        assertEquals(response.getStatus(),404);
        assertEquals(response.getEntity(),error);
    }

    @Test
    public void transfer_transactionWhichIsUnableToTransfer_returnBadResponseStatus() throws Exception {
        Integer senderAccountId = 100;

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setSenderAccountId(senderAccountId);

        String error = String.format("Account %d has insufficient funds", senderAccountId);

        doThrow(new InvalidFundsException(error)).when(transactionService).transfer(transactionDTO);

        Response response = transactionResource.transfer(transactionDTO);

        assertEquals(response.getStatus(),400);
        assertEquals(response.getEntity(),error);
    }


}
