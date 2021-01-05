package br.com.itau.api.passwordValidation.domain.validation;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class ValidatorOnceLowercaseTest {
  @Mock
  ConstraintValidatorContext context;

  @Test
  void validObject() {
    String password = "AbTp9!fok";

    ValidatorOnceLowercase validator = new ValidatorOnceLowercase();
    boolean response = validator.isValid(password, context);
    assertEquals(true, response);
  }

  @Test
  void invalidObject() {
    String password = "ABTP9!FOK";

    ValidatorOnceLowercase validator = new ValidatorOnceLowercase();
    boolean response = validator.isValid(password, context);
    assertEquals(false, response);
  }
}
