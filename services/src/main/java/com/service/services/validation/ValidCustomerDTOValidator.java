package com.service.services.validation;

import com.service.dto.CustomerDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidCustomerDTOValidator implements ConstraintValidator<ValidCustomerDTO, CustomerDTO> {

    @Override
    public void initialize(ValidCustomerDTO constraintAnnotation) {
    }

    @Override
    public boolean isValid(CustomerDTO customerDTO, ConstraintValidatorContext context) {
        if ( customerDTO == null ) {
            return true;
        }
        return customerDTO.getCustomerId() != null && customerDTO.getAddress() != null &&
                customerDTO.getFirstName() != null && customerDTO.getLastName() != null &&
                customerDTO.getNumber() != null && customerDTO.getLastModifiedTime() != null;
    }
}