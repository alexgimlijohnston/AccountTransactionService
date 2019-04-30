package com.service.dao.currency;

import com.service.common.enums.Currency;

public interface CurrencyDAO {

    Double convertCurrency(Currency senderCurrency, Currency receiverCurrency, Double amount);

}
