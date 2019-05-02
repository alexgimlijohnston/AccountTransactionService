package com.service.dao.transaction;


import com.service.common.enums.Currency;
import com.service.common.exceptions.AccountDoesNotExistException;
import com.service.common.exceptions.InvalidCurrencyConversionException;
import com.service.common.exceptions.InvalidFundsException;
import com.service.dao.DatabaseUtil;
import com.service.dao.account.AccountRepository;
import com.service.dao.currency.CurrencyDAO;
import com.service.dao.currency.CurrencyDAOImpl;
import com.service.domain.Account;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.util.Optional;

public class TransactionDAOImpl implements TransactionDAO {

    private CurrencyDAO currencyDAO;

    private EntityManager entityManager;

    public TransactionDAOImpl() {
        this.currencyDAO = new CurrencyDAOImpl();
    }

    public TransactionDAOImpl(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    @Override
    public void transfer(Integer senderAccountId, Integer receiverAccountId, BigDecimal amount, Currency currency)
            throws AccountDoesNotExistException, InvalidFundsException, InvalidCurrencyConversionException {
        try {
            DatabaseUtil.getEntityManager().getTransaction().begin();
            transferAmountFromSenderToReceiver(senderAccountId, receiverAccountId, amount, currency);
            DatabaseUtil.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            if (DatabaseUtil.getEntityManager().getTransaction() != null) {
                DatabaseUtil.getEntityManager().getTransaction().rollback();
            }
            throw e;
        }
    }

    private void transferAmountFromSenderToReceiver(Integer senderAccountId, Integer receiverAccountId, BigDecimal amount, Currency currency)
            throws AccountDoesNotExistException, InvalidFundsException, InvalidCurrencyConversionException {
        Optional<Account> senderAccount = AccountRepository.getAccountById(senderAccountId);
        Optional<Account> receiverAccount = AccountRepository.getAccountById(receiverAccountId);

        boolean senderExists = senderAccount.isPresent();
        boolean receiverExists = receiverAccount.isPresent();
        if(senderExists && receiverExists) {
            Account sender = senderAccount.get();
            Account receiver = receiverAccount.get();
            
            if(validateSenderAccount(sender, amount)) {
                BigDecimal amountInSendersCurrency = getAmountInCorrectCurrency(sender, currency, amount);
                BigDecimal amountInReceiversCurrency = getAmountInCorrectCurrency(receiver, currency, amount);

                sender.setBalance(sender.getBalance().subtract(amountInSendersCurrency));
                receiver.setBalance(receiver.getBalance().add(amountInReceiversCurrency));

                DatabaseUtil.getEntityManager().persist(sender);
                DatabaseUtil.getEntityManager().persist(receiver);
                DatabaseUtil.getEntityManager().flush();
            } else {
                throw new InvalidFundsException(String.format("Account %d does not have enough money", senderAccountId));
            }

        } else {
            String errorMessage;
            if(!senderExists && !receiverExists) {
                errorMessage = String.format("Accounts %d and %d do not exist", senderAccountId, receiverAccountId);
            } else if(!senderExists){
                errorMessage = String.format("Account %d does not exist", senderAccountId);
            } else {
                errorMessage = String.format("Account %d does not exist", receiverAccountId);
            }
            throw new AccountDoesNotExistException(errorMessage);
        }
    }

    private BigDecimal getAmountInCorrectCurrency(Account account, Currency currency, BigDecimal amount)
            throws InvalidCurrencyConversionException {
        return Currency.valueOf(account.getCurrency()).equals(currency) 
                ? amount : convertToDifferentCurrency(account, currency, amount);
    }

    private BigDecimal convertToDifferentCurrency(Account account, Currency fromCurrency, BigDecimal amount)
            throws InvalidCurrencyConversionException {
        Currency toCurrency = Currency.valueOf(account.getCurrency());
        return currencyDAO.convertCurrency(fromCurrency, toCurrency, amount);
    }

    private boolean validateSenderAccount(Account senderAccount, BigDecimal amount) {
        return (senderAccount.getBalance().add(senderAccount.getOverdraftAmount())).compareTo(amount) >= 0;
    }

}
