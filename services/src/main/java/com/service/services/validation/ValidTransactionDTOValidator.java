package com.service.services.validation;

import com.service.dto.TransactionDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class ValidTransactionDTOValidator implements ConstraintValidator<ValidTransactionDTO, TransactionDTO> {

    @Override
    public void initialize(ValidTransactionDTO constraintAnnotation) {
    }

    @Override
    public boolean isValid(TransactionDTO transactionDTO, ConstraintValidatorContext context) {
        if ( transactionDTO == null ) {
            return true;
        }
        return transactionDTO.getSenderAccountId() != null && transactionDTO.getReceiverAccountId() != null &&
                transactionDTO.getCurrency() != null && transactionDTO.getAmount() != null &&
                transactionDTO.getAmount().compareTo(BigDecimal.ZERO) >= 0;
    }
}