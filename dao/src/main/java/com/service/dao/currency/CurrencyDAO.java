package com.service.dao.currency;

import com.service.common.enums.Currency;
import com.service.common.exceptions.InvalidCurrencyConversionException;
import java.math.BigDecimal;

public interface CurrencyDAO {

    BigDecimal convertCurrency(Currency senderCurrency, Currency receiverCurrency, BigDecimal amount) throws InvalidCurrencyConversionException;

}
