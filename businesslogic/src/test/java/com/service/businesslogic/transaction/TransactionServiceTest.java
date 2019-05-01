package com.service.businesslogic.transaction;

import com.service.common.enums.Currency;
import com.service.common.exceptions.AccountDoesNotExistException;
import com.service.dao.transaction.TransactionDAO;
import com.service.dto.TransactionDTO;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    private TransactionDAO transactionDAO;

    private TransactionService transactionService;

    @Before
    public void setup() {
        transactionDAO = mock(TransactionDAO.class);

        transactionService = new TransactionServiceImpl(transactionDAO);
    }

    @Test
    public void transfer_validTransactionDto_callTransferMethodInDao() throws Exception {
        Integer senderId = 100;
        Integer receiverId = 200;
        BigDecimal amount = new BigDecimal(10);
        Currency currency = Currency.GBP;

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setSenderAccountId(senderId);
        transactionDTO.setReceiverAccountId(receiverId);
        transactionDTO.setAmount(amount);
        transactionDTO.setCurrency(currency);

        transactionService.transfer(transactionDTO);

        verify(transactionDAO, times(1)).transfer(senderId, receiverId, amount, currency);
    }

}
