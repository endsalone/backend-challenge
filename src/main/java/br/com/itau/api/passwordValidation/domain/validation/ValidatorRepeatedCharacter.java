package br.com.itau.api.passwordValidation.domain.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatorRepeatedCharacter implements ConstraintValidator<RepeatedCharacter, String> {
  private String regex = ".*[A-Z].*";

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if(value == null || value.isEmpty()) {
      return false;
    }

    for(int i = 0; i< value.length();i++)
    {
      for( int j = i+1; j< value.length();j++)
      {
        if(value.charAt(i) == value.charAt(j))
        {
          return false;
        }
      }
    }
    return true;
  }

}
