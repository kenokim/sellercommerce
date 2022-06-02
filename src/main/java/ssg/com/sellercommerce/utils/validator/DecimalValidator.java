package ssg.com.sellercommerce.utils.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DecimalValidator implements ConstraintValidator<DecimalConstraint, Long> {

    @Override
    public void initialize(DecimalConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value < 10000000000L && value >= 1000000000L;
    }
}
