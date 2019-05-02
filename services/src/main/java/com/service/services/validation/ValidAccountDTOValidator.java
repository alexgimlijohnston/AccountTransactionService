package com.service.services.validation;

import com.service.dto.AccountDTO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class ValidAccountDTOValidator implements ConstraintValidator<ValidAccountDTO, AccountDTO> {

    @Override
    public void initialize(ValidAccountDTO constraintAnnotation) {
    }

    @Override
    public boolean isValid(AccountDTO accountDTO, ConstraintValidatorContext context) {
        if ( accountDTO == null ) {
            return true;
        }
        return accountDTO.getAccountId() != null && accountDTO.getBalance() != null &&
                accountDTO.getBalance().compareTo(BigDecimal.ZERO) >= 0 &&
                accountDTO.getCurrency() != null && accountDTO.getOverdraftAmount() != null &&
                accountDTO.getOverdraftAmount().compareTo(BigDecimal.ZERO) >= 0 &&
                accountDTO.getSortCode() != null && accountDTO.getLastModifiedTime() != null;
    }
}
