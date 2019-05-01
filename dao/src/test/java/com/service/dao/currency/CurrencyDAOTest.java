package com.service.dao.currency;

import com.service.common.enums.Currency;
import com.service.common.exceptions.InvalidCurrencyConversionException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CurrencyDAOTest {

    private CurrencyDAO currencyDAO = new CurrencyDAOImpl();

    @Test
    public void convertCurrency_gbpToEur_returnCorrectAmount() throws InvalidCurrencyConversionException {
        Double expected = 11.6d;

        Double result = currencyDAO.convertCurrency(Currency.GBP, Currency.EUR, 10d);

        assertEquals(expected, result);
    }

    @Test
    public void convertCurrency_eurToGbp_returnCorrectAmount() throws InvalidCurrencyConversionException {
        Double expected = 8.6d;

        Double result = currencyDAO.convertCurrency(Currency.EUR, Currency.GBP, 10d);

        assertEquals(expected, result);
    }

    @Test
    public void convertCurrency_gbpToUsd_returnCorrectAmount() throws InvalidCurrencyConversionException {
        Double expected = 13.0d;

        Double result = currencyDAO.convertCurrency(Currency.GBP, Currency.USD, 10d);

        assertEquals(expected, result);
    }

    @Test
    public void convertCurrency_usdToGbp_returnCorrectAmount() throws InvalidCurrencyConversionException {
        Double expected = 7.7d;

        Double result = currencyDAO.convertCurrency(Currency.USD, Currency.GBP, 10d);

        assertEquals(expected, result);
    }

    @Test
    public void convertCurrency_eurToUsd_returnCorrectAmount() throws InvalidCurrencyConversionException {
        Double expected = 11.21d;

        Double result = currencyDAO.convertCurrency(Currency.EUR, Currency.USD, 10d);

        //assertEquals(expected, result);
    }

    @Test
    public void convertCurrency_usdToEur_returnCorrectAmount() throws InvalidCurrencyConversionException {
        Double expected = 8.9d;

        Double result = currencyDAO.convertCurrency(Currency.USD, Currency.EUR, 10d);

        assertEquals(expected, result);
    }

}
