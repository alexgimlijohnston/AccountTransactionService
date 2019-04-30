package com.service.businesslogic.account;

import com.service.domain.Account;
import com.service.dto.AccountDTO;
import com.service.common.enums.Currency;
import org.joda.time.DateTime;

import java.sql.Timestamp;

class AccountMapper {

    AccountDTO mapAccountToAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(account.getAccountId());
        accountDTO.setSortCode(account.getSortCode());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setOverdraftAmount(account.getOverdraftAmount());
        accountDTO.setLastModifiedTime(new DateTime(account.getLastModifiedTime()));
        accountDTO.setCurrency(Currency.valueOf(account.getCurrency()));
        return accountDTO;
    }

    Account mapAccountDTOToAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setAccountId(accountDTO.getAccountId());
        account.setSortCode(accountDTO.getSortCode());
        account.setBalance(accountDTO.getBalance());
        account.setOverdraftAmount(accountDTO.getOverdraftAmount());
        account.setLastModifiedTime(new Timestamp(accountDTO.getLastModifiedTime().getMillis()));
        account.setCurrency(accountDTO.getCurrency().getName());
        return account;
    }

}
