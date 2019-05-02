package com.service.dao.transaction;

import com.service.common.enums.Currency;
import com.service.common.exceptions.AccountDoesNotExistException;
import com.service.common.exceptions.InvalidFundsException;
import com.service.dao.DatabaseUtil;
import com.service.dao.account.AccountDAO;
import com.service.dao.account.AccountDAOImpl;
import com.service.dao.currency.CurrencyDAO;
import com.service.dao.currency.CurrencyDAOImpl;
import com.service.domain.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DatabaseUtil.class})
public class TransactionDAOTest {

    private CurrencyDAO currencyDAO;

    private AccountDAO accountDAO;

    private TransactionDAO transactionDAO;

    @Before
    public void initMocks() {
        currencyDAO = mock(CurrencyDAOImpl.class);
        accountDAO = mock(AccountDAOImpl.class);
        transactionDAO = new TransactionDAOImpl(currencyDAO, accountDAO);

        mockStatic(DatabaseUtil.class);
    }

    @Test
    public void transfer_validAccountsAndAmountAndSameCurrency_amountIsTransferred() throws Exception {
        EntityManager entityManager = mock(EntityManager.class);
        EntityTransaction transaction = mock(EntityTransaction.class);
        Integer senderId = 1000;
        Integer receiverId = 2000;

        Account senderAccount = new Account();
        senderAccount.setAccountId(1000);
        senderAccount.setBalance(new BigDecimal(500));
        senderAccount.setOverdraftAmount(new BigDecimal(100));
        senderAccount.setCurrency("GBP");

        Account receiverAccount = new Account();
        receiverAccount.setAccountId(1000);
        receiverAccount.setBalance(new BigDecimal(400));
        receiverAccount.setCurrency("GBP");

        when(DatabaseUtil.getEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(transaction);
        when(accountDAO.getAccountById(senderId)).thenReturn(Optional.of(senderAccount));
        when(accountDAO.getAccountById(receiverId)).thenReturn(Optional.of(receiverAccount));

        transactionDAO.transfer(senderId, receiverId, new BigDecimal(150), Currency.GBP);

        assertEquals(senderAccount.getBalance(), new BigDecimal(350));
    }

    @Test(expected = AccountDoesNotExistException.class)
    public void transfer_invalidSenderAccount_throwAccountDoesNotExistException() throws Exception {
        EntityManager entityManager = mock(EntityManager.class);
        EntityTransaction transaction = mock(EntityTransaction.class);
        Integer senderId = 1000;
        Integer receiverId = 2000;

        Account receiverAccount = new Account();
        receiverAccount.setAccountId(1000);
        receiverAccount.setBalance(new BigDecimal(400));
        receiverAccount.setCurrency("GBP");

        when(DatabaseUtil.getEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(transaction);
        when(accountDAO.getAccountById(senderId)).thenReturn(Optional.empty());
        when(accountDAO.getAccountById(receiverId)).thenReturn(Optional.of(receiverAccount));

        transactionDAO.transfer(senderId, receiverId, new BigDecimal(150), Currency.GBP);
    }

    @Test(expected = AccountDoesNotExistException.class)
    public void transfer_invalidReceiverAccount_throwAccountDoesNotExistException() throws Exception {
        EntityManager entityManager = mock(EntityManager.class);
        EntityTransaction transaction = mock(EntityTransaction.class);
        Integer senderId = 1000;
        Integer receiverId = 2000;

        Account senderAccount = new Account();
        senderAccount.setAccountId(1000);
        senderAccount.setBalance(new BigDecimal(400));
        senderAccount.setCurrency("GBP");

        when(DatabaseUtil.getEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(transaction);
        when(accountDAO.getAccountById(senderId)).thenReturn(Optional.of(senderAccount));
        when(accountDAO.getAccountById(receiverId)).thenReturn(Optional.empty());

        transactionDAO.transfer(senderId, receiverId, new BigDecimal(150), Currency.GBP);
    }

    @Test(expected = InvalidFundsException.class)
    public void transfer_senderDoesNotHaveEnoughFunds_throwInvalidFundsException() throws Exception {
        EntityManager entityManager = mock(EntityManager.class);
        EntityTransaction transaction = mock(EntityTransaction.class);
        Integer senderId = 1000;
        Integer receiverId = 2000;

        Account senderAccount = new Account();
        senderAccount.setAccountId(1000);
        senderAccount.setBalance(new BigDecimal(500));
        senderAccount.setOverdraftAmount(new BigDecimal(100));
        senderAccount.setCurrency("GBP");

        Account receiverAccount = new Account();
        receiverAccount.setAccountId(1000);
        receiverAccount.setBalance(new BigDecimal(400));
        receiverAccount.setCurrency("GBP");

        when(DatabaseUtil.getEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(transaction);
        when(accountDAO.getAccountById(senderId)).thenReturn(Optional.of(senderAccount));
        when(accountDAO.getAccountById(receiverId)).thenReturn(Optional.of(receiverAccount));

        transactionDAO.transfer(senderId, receiverId, new BigDecimal(900), Currency.GBP);
    }

    @Test
    public void transfer_validAccountsAndAmountButDifferentCurrency_amountIsConvertedAndTransferred() throws Exception {
        EntityManager entityManager = mock(EntityManager.class);
        EntityTransaction transaction = mock(EntityTransaction.class);
        Integer senderId = 1000;
        Integer receiverId = 2000;
        BigDecimal amount = new BigDecimal(150);

        Account senderAccount = new Account();
        senderAccount.setAccountId(1000);
        senderAccount.setBalance(new BigDecimal(500));
        senderAccount.setOverdraftAmount(new BigDecimal(100));
        senderAccount.setCurrency("GBP");

        Account receiverAccount = new Account();
        receiverAccount.setAccountId(1000);
        receiverAccount.setBalance(new BigDecimal(400));
        receiverAccount.setCurrency("USD");

        when(DatabaseUtil.getEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(transaction);
        when(accountDAO.getAccountById(senderId)).thenReturn(Optional.of(senderAccount));
        when(accountDAO.getAccountById(receiverId)).thenReturn(Optional.of(receiverAccount));

        when(currencyDAO.convertCurrency(Currency.EUR, Currency.GBP, amount)).thenReturn(new BigDecimal(129));
        when(currencyDAO.convertCurrency(Currency.EUR, Currency.USD, amount)).thenReturn(new BigDecimal(173));

        transactionDAO.transfer(senderId, receiverId, amount, Currency.EUR);

        assertEquals(senderAccount.getBalance(), new BigDecimal(371));
        assertEquals(receiverAccount.getBalance(), new BigDecimal(573));
    }


}
