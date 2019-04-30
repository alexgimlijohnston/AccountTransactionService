package com.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import java.util.Objects;

public class CustomerDTO {

    private Integer customerId;

    private String firstName;

    private String lastName;

    private String address;

    private String number;

    private DateTime lastModifiedTime;

    public CustomerDTO(){

    }

    public CustomerDTO(Integer customerId, String firstName, String lastName, String address, String number, DateTime lastModifiedTime) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.number = number;
        this.lastModifiedTime = lastModifiedTime;
    }

    @JsonProperty
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @JsonProperty
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
        CustomerDTO that = (CustomerDTO) o;
        return customerId.equals(that.customerId) &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                Objects.equals(address, that.address) &&
                Objects.equals(number, that.number) &&
                lastModifiedTime.equals(that.lastModifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, address, number, lastModifiedTime);
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", number='" + number + '\'' +
                ", lastModifiedTime=" + lastModifiedTime +
                '}';
    }
}
