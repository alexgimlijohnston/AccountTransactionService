package com.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountDTO {

    private Integer accountId;

    @JsonProperty
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }



}
