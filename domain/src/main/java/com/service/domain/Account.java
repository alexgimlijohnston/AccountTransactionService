package com.service.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account implements Serializable {

    @Id
    @Column(name = "accountId")
    private Integer accountId;

    @Column(name = "sortCode")
    private String sortCode;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "currency")
    private String currency;

    @Column(name = "overdraftAmount")
    private Double overdraftAmount;

    @Column(name = "lastModifiedTime")
    private Timestamp lastModifiedTime;

    public Account(){}

    public Account(Integer accountId, String sortCode) {
        this.accountId = accountId;
        this.sortCode = sortCode;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getOverdraftAmount() {
        return overdraftAmount;
    }

    public void setOverdraftAmount(Double overdraftAmount) {
        this.overdraftAmount = overdraftAmount;
    }

    public Timestamp getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Timestamp lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId.equals(account.accountId) &&
                sortCode.equals(account.sortCode) &&
                balance.equals(account.balance) &&
                currency.equals(account.currency) &&
                overdraftAmount.equals(account.overdraftAmount) &&
                lastModifiedTime.equals(account.lastModifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, sortCode, balance, currency, overdraftAmount, lastModifiedTime);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", sortCode='" + sortCode + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", overdraftAmount=" + overdraftAmount +
                ", lastModifiedTime=" + lastModifiedTime +
                '}';
    }
}