package com.service.businesslogic.customer;

import com.service.domain.Customer;
import com.service.dto.CustomerDTO;

public class CustomerMapper {

    CustomerDTO mapCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setLastModifiedTime(customer.getLastModifiedTime());
        customerDTO.setNumber(customer.getNumber());
        return customerDTO;
    }

    Customer mapCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setAddress(customerDTO.getAddress());
        customer.setLastModifiedTime(customerDTO.getLastModifiedTime());
        customer.setNumber(customerDTO.getNumber());
        return customer;
    }


}
