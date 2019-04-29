package com.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountDTO {

    private Integer accountId;

    private Double balance;

    @JsonProperty
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @JsonProperty
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
