package com.service.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "accountCustomerMapping")
public class AccountCustomerMapping {

    @EmbeddedId
    private AccountCustomerMappingId accountCustomerMappingId;

    public AccountCustomerMappingId getAccountCustomerMappingId() {
        return accountCustomerMappingId;
    }

    public void setAccountCustomerMappingId(AccountCustomerMappingId accountCustomerMappingId) {
        this.accountCustomerMappingId = accountCustomerMappingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountCustomerMapping that = (AccountCustomerMapping) o;
        return Objects.equals(accountCustomerMappingId, that.accountCustomerMappingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountCustomerMappingId);
    }

    @Override
    public String toString() {
        return "AccountCustomerMapping{" +
                "accountCustomerMappingId=" + accountCustomerMappingId +
                '}';
    }
}
