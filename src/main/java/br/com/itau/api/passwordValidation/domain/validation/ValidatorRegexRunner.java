package br.com.itau.api.passwordValidation.domain.validation;

import java.util.regex.Pattern;

public class ValidatorRegexRunner {
  public static boolean check(String regex, String value) {
    if(value == null || value.isEmpty()) {
      return false;
    }

    return Pattern.matches(regex, value);
  }
}
