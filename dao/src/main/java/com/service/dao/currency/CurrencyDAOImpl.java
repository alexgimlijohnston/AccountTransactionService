package com.service.dao.currency;

import com.service.common.enums.Currency;
import com.service.common.exceptions.InvalidCurrencyConversionException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyDAOImpl implements CurrencyDAO {

    /*
     This is test data - in reality we would go to another service to retrieve this data
      */
    private final BigDecimal gbpToEur = new BigDecimal(1.16);
    private final BigDecimal eurToGbp = new BigDecimal(0.86);
    private final BigDecimal gbpToUsd = new BigDecimal(1.30);
    private final BigDecimal usdToGbp = new BigDecimal(0.77);
    private final BigDecimal eurToUsd = new BigDecimal(1.12);
    private final BigDecimal usdToEur = new BigDecimal(0.89);

    @Override
    public BigDecimal convertCurrency(Currency fromCurrency, Currency toCurrency, BigDecimal amount) throws InvalidCurrencyConversionException {
        if(fromCurrency == Currency.GBP && toCurrency == Currency.EUR) return amount.multiply(gbpToEur).setScale(2, RoundingMode.HALF_UP);
        if(fromCurrency == Currency.EUR && toCurrency == Currency.GBP) return amount.multiply(eurToGbp).setScale(2, RoundingMode.HALF_UP);
        if(fromCurrency == Currency.GBP && toCurrency == Currency.USD) return amount.multiply(gbpToUsd).setScale(2, RoundingMode.HALF_UP);
        if(fromCurrency == Currency.USD && toCurrency == Currency.GBP) return amount.multiply(usdToGbp).setScale(2, RoundingMode.HALF_UP);
        if(fromCurrency == Currency.EUR && toCurrency == Currency.USD) return amount.multiply(eurToUsd).setScale(2, RoundingMode.HALF_UP);
        if(fromCurrency == Currency.USD && toCurrency == Currency.EUR) return amount.multiply(usdToEur).setScale(2, RoundingMode.HALF_UP);
        else throw new InvalidCurrencyConversionException(String.format("Unable to compute currency conversion from %s to %s", fromCurrency, toCurrency));
    }

}
