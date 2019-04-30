package com.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.common.enums.Currency;
import java.util.Objects;

public class TransactionDTO {

    private Integer senderAccountId;

    private Integer receiverAccountId;

    private Double amount;

    private Currency currency;

    @JsonProperty
    public Integer getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(Integer senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    @JsonProperty
    public Integer getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(Integer receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    @JsonProperty
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @JsonProperty
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDTO that = (TransactionDTO) o;
        return Objects.equals(senderAccountId, that.senderAccountId) &&
                Objects.equals(receiverAccountId, that.receiverAccountId) &&
                Objects.equals(amount, that.amount) &&
                currency == that.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderAccountId, receiverAccountId, amount, currency);
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "senderAccountId=" + senderAccountId +
                ", receiverAccountId=" + receiverAccountId +
                ", amount=" + amount +
                ", currency=" + currency +
                '}';
    }
}
