package br.com.itau.api.passwordValidation.domain.validation;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class ValidatorRegexRunnerTest {

  @Test
  void validObject() {
    String password = "itau!top";
    String regex = ".*[/\\s].*";
    boolean validator = ValidatorRegexRunner.check(regex, password);
    assertEquals(false, validator);
  }

  @Test
  void invalidObject() {
    String password = "itau top";
    String regex = ".*[/\\s].*";
    boolean validator = ValidatorRegexRunner.check(regex, password);
    assertEquals(true, validator);
  }
}
