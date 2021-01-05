package br.com.itau.api.passwordValidation.application.routes;

import br.com.itau.api.passwordValidation.domain.PasswordRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class ValidationControllerTest {

  @Test
  void validation() {
    String password = "AbTp9!fok";

    PasswordRequest payload = new PasswordRequest();
    payload.setPassword(password);

    ValidationController controller = new ValidationController();
    ResponseEntity<Boolean> response = controller.validation(payload);
    assertEquals(true, response.getBody());
    assertEquals(200, response.getStatusCodeValue());
  }


}
