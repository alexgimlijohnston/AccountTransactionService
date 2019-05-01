package com.service.dao.currency;

import com.service.common.enums.Currency;
import com.service.common.exceptions.InvalidCurrencyConversionException;
import org.junit.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class CurrencyDAOTest {

    private CurrencyDAO currencyDAO = new CurrencyDAOImpl();

    @Test
    public void convertCurrency_gbpToEur_returnCorrectAmount() throws InvalidCurrencyConversionException {
        BigDecimal expected = new BigDecimal(11.6).setScale(2, RoundingMode.HALF_UP);

        BigDecimal result = currencyDAO.convertCurrency(Currency.GBP, Currency.EUR, BigDecimal.TEN);

        assertEquals(expected, result);
    }

    @Test
    public void convertCurrency_eurToGbp_returnCorrectAmount() throws InvalidCurrencyConversionException {
        BigDecimal expected = new BigDecimal(8.6).setScale(2, RoundingMode.HALF_UP);

        BigDecimal result = currencyDAO.convertCurrency(Currency.EUR, Currency.GBP, BigDecimal.TEN);

        assertEquals(expected, result);
    }

    @Test
    public void convertCurrency_gbpToUsd_returnCorrectAmount() throws InvalidCurrencyConversionException {
        BigDecimal expected = new BigDecimal(13.0).setScale(2, RoundingMode.HALF_UP);

        BigDecimal result = currencyDAO.convertCurrency(Currency.GBP, Currency.USD, BigDecimal.TEN);

        assertEquals(expected, result);
    }

    @Test
    public void convertCurrency_usdToGbp_returnCorrectAmount() throws InvalidCurrencyConversionException {
        BigDecimal expected = new BigDecimal(7.7).setScale(2, RoundingMode.HALF_UP);

        BigDecimal result = currencyDAO.convertCurrency(Currency.USD, Currency.GBP, BigDecimal.TEN);

        assertEquals(expected, result);
    }

    @Test
    public void convertCurrency_eurToUsd_returnCorrectAmount() throws InvalidCurrencyConversionException {
        BigDecimal expected = new BigDecimal(11.2).setScale(2, RoundingMode.HALF_UP);

        BigDecimal result = currencyDAO.convertCurrency(Currency.EUR, Currency.USD, BigDecimal.TEN);

        assertEquals(expected, result);
    }

    @Test
    public void convertCurrency_usdToEur_returnCorrectAmount() throws InvalidCurrencyConversionException {
        BigDecimal expected = new BigDecimal(8.9).setScale(2, RoundingMode.HALF_UP);

        BigDecimal result = currencyDAO.convertCurrency(Currency.USD, Currency.EUR, BigDecimal.TEN);

        assertEquals(expected, result);
    }

}
