package com.service.dao.transaction;

import com.service.common.enums.Currency;
import java.math.BigDecimal;

public interface TransactionDAO {

    void transfer(Integer senderAccountId, Integer receiverAccountId, BigDecimal amount, Currency currency) throws Exception;

}
