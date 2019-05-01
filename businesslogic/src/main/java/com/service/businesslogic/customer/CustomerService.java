package com.service.businesslogic.customer;

import com.service.dto.CustomerDTO;
import java.util.Optional;

public interface CustomerService {

    Optional<CustomerDTO> getCustomerById(Integer id);

    void createCustomer(CustomerDTO customerDTO) throws Exception;

}
