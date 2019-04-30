package com.service.businesslogic.customer;

import com.service.domain.Customer;
import com.service.dto.CustomerDTO;
import org.joda.time.DateTime;

import java.sql.Timestamp;

public class CustomerMapper {

    CustomerDTO mapCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setLastModifiedTime(new DateTime(customer.getLastModifiedTime()));
        customerDTO.setNumber(customer.getNumber());
        return customerDTO;
    }

    Customer mapCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setAddress(customerDTO.getAddress());
        customer.setLastModifiedTime(new Timestamp(customerDTO.getLastModifiedTime().getMillis()));
        customer.setNumber(customerDTO.getNumber());
        return customer;
    }


}
