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
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Optional;

public class TransactionDAOImpl implements TransactionDAO {

    private CurrencyDAO currencyDAO;

    public TransactionDAOImpl() {
        currencyDAO = new CurrencyDAOImpl();
    }

    @Override
    public void transfer(Integer senderAccountId, Integer receiverAccountId, Double amount, Currency currency) {
        Session session = DatabaseUtil.getNewSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            transferAmountFromSenderToReceiver(senderAccountId, receiverAccountId, amount, currency, session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    private void transferAmountFromSenderToReceiver(Integer senderAccountId, Integer receiverAccountId, Double amount, Currency currency, Session session) throws Exception {
        Optional<Account> senderAccount = AccountRepository.getAccountById(session, senderAccountId);
        Optional<Account> receiverAccount = AccountRepository.getAccountById(session, receiverAccountId);

        if(senderAccount.isPresent() && receiverAccount.isPresent()) {
            Account sender = senderAccount.get();
            Account receiver = receiverAccount.get();
            
            if(validateSenderAccount(sender, amount)) {
                double amountInSendersCurrency = getAmountInCorrectCurrency(sender, currency, amount);
                double amountInReceiversCurrency = getAmountInCorrectCurrency(receiver, currency, amount);

                sender.setBalance(sender.getBalance() - amountInSendersCurrency);
                receiver.setBalance(receiver.getBalance() + amountInReceiversCurrency);
                
                session.update(sender);
                session.update(receiver);
            } else {
                throw new InvalidFundsException("Sender's account does not have enough money");
            }
        } else {
            throw new AccountDoesNotExistException("One of the accounts does not exist");
        }
    }

    private double getAmountInCorrectCurrency(Account account, Currency currency, Double amount) throws InvalidCurrencyConversionException {
        return Currency.valueOf(account.getCurrency()).equals(currency) 
                ? amount : convertToDifferentCurrency(account, currency, amount);
    }

    private Double convertToDifferentCurrency(Account account, Currency fromCurrency, Double amount) throws InvalidCurrencyConversionException {
        Currency toCurrency = Currency.valueOf(account.getCurrency());
        return currencyDAO.convertCurrency(fromCurrency, toCurrency, amount);
    }

    private boolean validateSenderAccount(Account senderAccount, Double amount) {
        return (senderAccount.getBalance() + senderAccount.getOverdraftAmount()) >= amount;
    }

}
