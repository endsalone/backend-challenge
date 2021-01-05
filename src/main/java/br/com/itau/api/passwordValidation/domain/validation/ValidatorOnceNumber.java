package br.com.itau.api.passwordValidation.domain.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatorOnceNumber implements ConstraintValidator<OnceNumber, String> {
  private String regex = ".*[0-9].*";

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    boolean check = ValidatorRegexRunner.check(regex, value);
    return check;
  }
}
