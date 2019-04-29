package com.service.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "customerId")
    private Integer customerId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "number")
    private String number;

    @Column(name = "lastModifiedTime")
    private Timestamp lastModifiedTime;

    public Customer(){

    }

    public Customer(Integer customerId, String firstName, String lastName, String address, String number, Timestamp lastModifiedTime) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.number = number;
        this.lastModifiedTime = lastModifiedTime;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
        Customer customer = (Customer) o;
        return customerId.equals(customer.customerId) &&
                firstName.equals(customer.firstName) &&
                lastName.equals(customer.lastName) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(number, customer.number) &&
                lastModifiedTime.equals(customer.lastModifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, address, number, lastModifiedTime);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", number='" + number + '\'' +
                ", lastModifiedTime=" + lastModifiedTime +
                '}';
    }
}
