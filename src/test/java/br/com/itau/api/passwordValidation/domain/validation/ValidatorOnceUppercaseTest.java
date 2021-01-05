package br.com.itau.api.passwordValidation.domain.validation;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class ValidatorOnceUppercaseTest {
  @Mock
  ConstraintValidatorContext context;

  @Test
  void validObject() {
    String password = "AbTp9!fok";

    ValidatorOnceUppercase validator = new ValidatorOnceUppercase();
    boolean response = validator.isValid(password, context);
    assertEquals(true, response);
  }

  @Test
  void invalidObject() {
    String password = "abtp9!fok";

    ValidatorOnceUppercase validator = new ValidatorOnceUppercase();
    boolean response = validator.isValid(password, context);
    assertEquals(false, response);
  }
}
