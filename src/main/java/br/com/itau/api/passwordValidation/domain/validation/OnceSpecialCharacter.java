package br.com.itau.api.passwordValidation.domain.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE })
@Constraint(validatedBy = ValidatorOnceSpecialCharacter.class)
public @interface OnceSpecialCharacter {
  String message() default "{password.invalid}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
