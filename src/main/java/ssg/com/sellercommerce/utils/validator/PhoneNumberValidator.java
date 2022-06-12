package ssg.com.sellercommerce.utils.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        try {
            long number = Long.parseLong(value);
            // 02-123-4567 ~ 010-1234-5678
            return number > 20000000L && number < 2000000000L;
        } catch (Exception e) {
            return false;
        }
    }
}