package com.service.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AccountCustomerMappingId implements Serializable {

    @Column(name = "accountId")
    private Integer accountId;

    @Column(name = "customerId")
    private Integer customerId;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountCustomerMappingId that = (AccountCustomerMappingId) o;
        return Objects.equals(accountId, that.accountId) &&
                Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, customerId);
    }

    @Override
    public String toString() {
        return "AccountCustomerMapping{" +
                "accountId=" + accountId +
                ", customerId=" + customerId +
                '}';
    }

}
