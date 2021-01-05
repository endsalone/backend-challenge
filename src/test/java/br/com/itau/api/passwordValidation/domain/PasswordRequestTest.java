package br.com.itau.api.passwordValidation.domain;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintValidator;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class PasswordRequestTest {
  @Test
  void validObject() {
    String password = "AbTp9!fok";
    PasswordRequest passwordRequest = new PasswordRequest(password);
    assertEquals(password, passwordRequest.getPassword());
  }
}
