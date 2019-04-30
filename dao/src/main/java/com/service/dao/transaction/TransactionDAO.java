package com.service.dao.transaction;

import com.service.common.enums.Currency;

public interface TransactionDAO {

    void transfer(Integer senderAccountId, Integer receiverAccountId, Double amount, Currency currency);

}
