package com.service.dao.currency;

import com.service.common.enums.Currency;

public class CurrencyDAOImpl implements CurrencyDAO {

    /*
     This is test data - in reality we would go to another service to retrieve this data
      */
    private final Double gbpToEur = 1.16;
    private final Double eurToGbp = 0.86;
    private final Double gbpToUsd = 1.30;
    private final Double usdToGbp = 0.77;
    private final Double eurToUsd = 1.12;
    private final Double usdToEur = 0.89;

    @Override
    public Double convertCurrency(Currency fromCurrency, Currency toCurrency, Double amount) {
        if(fromCurrency == Currency.GBP && toCurrency == Currency.EUR) return amount * gbpToEur;
        if(fromCurrency == Currency.EUR && toCurrency == Currency.GBP) return amount * eurToGbp;
        if(fromCurrency == Currency.GBP && toCurrency == Currency.USD) return amount * gbpToUsd;
        if(fromCurrency == Currency.USD && toCurrency == Currency.GBP) return amount * usdToGbp;
        if(fromCurrency == Currency.EUR && toCurrency == Currency.USD) return amount * eurToUsd;
        if(fromCurrency == Currency.USD && toCurrency == Currency.EUR) return amount * usdToEur;
        else throw new RuntimeException(String.format("Unable to compute currency conversion from %s to %s", fromCurrency, toCurrency));
    }

}
