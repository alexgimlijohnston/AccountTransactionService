package com.service.businesslogic.transaction;

import com.service.dao.transaction.TransactionDAO;
import com.service.dao.transaction.TransactionDAOImpl;
import com.service.dto.TransactionDTO;

public class TransactionServiceImpl implements TransactionService {

    private TransactionDAO transactionDAO;

    public TransactionServiceImpl() {
        this.transactionDAO = new TransactionDAOImpl();
    }

    @Override
    public void transfer(TransactionDTO transactionDTO) {
        transactionDAO.transfer(transactionDTO.getSenderAccountId(), transactionDTO.getReceiverAccountId(),
                transactionDTO.getAmount(), transactionDTO.getCurrency());
    }

}
