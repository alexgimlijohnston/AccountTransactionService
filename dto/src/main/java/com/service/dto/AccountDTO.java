package com.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountDTO {

    private Integer accountId;

    private String accountHolderName;

    @JsonProperty
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @JsonProperty
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
}
