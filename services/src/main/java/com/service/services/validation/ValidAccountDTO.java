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
@Constraint(validatedBy = { ValidAccountDTOValidator.class })
@Documented
public @interface ValidAccountDTO {

    String message() default "Invalid Account DTO";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}