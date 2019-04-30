package com.service.businesslogic.transaction;

import com.service.dto.TransactionDTO;

public interface TransactionService {

    void transfer(TransactionDTO transactionDTO);

}
