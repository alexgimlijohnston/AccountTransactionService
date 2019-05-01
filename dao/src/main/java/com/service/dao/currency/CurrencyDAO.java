package com.service.dao.currency;

import com.service.common.enums.Currency;
import com.service.common.exceptions.InvalidCurrencyConversionException;

public interface CurrencyDAO {

    Double convertCurrency(Currency senderCurrency, Currency receiverCurrency, Double amount) throws InvalidCurrencyConversionException;

}
