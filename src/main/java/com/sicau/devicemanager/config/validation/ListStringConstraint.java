package com.sicau.devicemanager.config.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ListStringValidator.class)
public @interface ListStringConstraint {
    String regexp();

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };
}
