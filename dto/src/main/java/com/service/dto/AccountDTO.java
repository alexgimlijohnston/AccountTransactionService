package com.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.common.enums.Currency;
import org.joda.time.DateTime;
import java.math.BigDecimal;
import java.util.Objects;

public class AccountDTO {

    private Integer accountId;

    private String sortCode;

    private BigDecimal balance;

    private Currency currency;

    private BigDecimal overdraftAmount;

    private DateTime lastModifiedTime;

    public AccountDTO(Integer accountId, String sortCode, BigDecimal balance, BigDecimal overdraftAmount, DateTime lastModifiedTime, Currency currency) {
        this.accountId = accountId;
        this.sortCode = sortCode;
        this.balance = balance;
        this.overdraftAmount = overdraftAmount;
        this.lastModifiedTime = lastModifiedTime;
        this.currency = currency;
    }

    public AccountDTO() {
    }

    @JsonProperty
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @JsonProperty
    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    @JsonProperty
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @JsonProperty
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @JsonProperty
    public BigDecimal getOverdraftAmount() {
        return overdraftAmount;
    }

    public void setOverdraftAmount(BigDecimal overdraftAmount) {
        this.overdraftAmount = overdraftAmount;
    }

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public DateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(DateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDTO that = (AccountDTO) o;
        return accountId.equals(that.accountId) &&
                sortCode.equals(that.sortCode) &&
                balance.equals(that.balance) &&
                currency == that.currency &&
                overdraftAmount.equals(that.overdraftAmount) &&
                lastModifiedTime.equals(that.lastModifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, sortCode, balance, currency, overdraftAmount, lastModifiedTime);
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "accountId=" + accountId +
                ", sortCode='" + sortCode + '\'' +
                ", balance=" + balance +
                ", currency=" + currency +
                ", overdraftAmount=" + overdraftAmount +
                ", lastModifiedTime=" + lastModifiedTime +
                '}';
    }
}
