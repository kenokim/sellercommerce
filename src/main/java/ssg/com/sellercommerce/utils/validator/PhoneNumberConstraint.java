package ssg.com.sellercommerce.utils.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberConstraint {
    String message() default "올바르지 않은 전화번호입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}