package com.sicau.devicemanager.config.validation;

import com.sicau.devicemanager.config.validation.ListStringConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.regex.Pattern;

public class ListStringValidator implements ConstraintValidator<ListStringConstraint, List<?>> {
    private String regexp;

    @Override
    public void initialize(ListStringConstraint constraintAnnotation) {
        this.regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {

        if (null == value) {
            return true;
        }

        boolean isValid = true;

        Pattern pattern = Pattern.compile(regexp);


        for (Object e : value) {
            String s = (String) e;
            if (!pattern.matcher(s).find()) {
                isValid = false;
                break;
            }
        }

        return isValid;
    }
}
