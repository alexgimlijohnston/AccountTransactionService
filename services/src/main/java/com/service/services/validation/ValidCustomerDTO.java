package com.service.services.validation;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
@Target({ PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ValidCustomerDTOValidator.class })
@Documented
public @interface ValidCustomerDTO {

    String message() default "Invalid Customer DTO";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}