package br.com.itau.api.passwordValidation.domain.validation;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class ValidatorOnceSpecialCharacterTest {
  @Mock
  ConstraintValidatorContext context;

  @Test
  void validObject() {
    String password = "AbTp9!fok";

    ValidatorOnceSpecialCharacter validator = new ValidatorOnceSpecialCharacter();
    boolean response = validator.isValid(password, context);
    assertEquals(true, response);
  }

  @Test
  void invalidObject() {
    String password = "AbTp9nfok";

    ValidatorOnceSpecialCharacter validator = new ValidatorOnceSpecialCharacter();
    boolean response = validator.isValid(password, context);
    assertEquals(false, response);
  }
}
