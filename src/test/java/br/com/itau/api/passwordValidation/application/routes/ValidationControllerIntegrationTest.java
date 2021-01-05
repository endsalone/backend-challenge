package br.com.itau.api.passwordValidation.application.routes;

import br.com.itau.api.passwordValidation.domain.PasswordRequest;
import br.com.itau.api.passwordValidation.domain.dto.response.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ValidationControllerIntegrationTest {

  @LocalServerPort
  private int port;

  private TestRestTemplate restTemplate = new TestRestTemplate();

  HttpHeaders headers = new HttpHeaders();

  @Test
  void validPassword() {

    PasswordRequest requestPayload = new PasswordRequest("AbTp9!fok");

    HttpEntity<PasswordRequest> entity = new HttpEntity<PasswordRequest>(requestPayload, headers);

    ResponseEntity<Boolean> response = post(entity, Boolean.class, "/api/passwords/validation");

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(true, response.getBody());

  }

  @Test
  void missingLowerCase() {
    PasswordRequest requestPayload = new PasswordRequest("ABTP9!FOK");

    HttpEntity<PasswordRequest> entity = new HttpEntity<PasswordRequest>(requestPayload, headers);

    ResponseEntity<ErrorResponse> response = post(entity, ErrorResponse.class, "/api/passwords/validation");

    List<String> errorMsg = new ArrayList<String>();
    errorMsg.add("A senha deve conter ao menos uma letra minuscula");

    assertEquals(400, response.getStatusCodeValue());
    assertEquals(errorMsg, response.getBody().getErrors());
  }

  @Test
  void missingUpperCase() {
    PasswordRequest requestPayload = new PasswordRequest("abtp9!fok");

    HttpEntity<PasswordRequest> entity = new HttpEntity<PasswordRequest>(requestPayload, headers);

    ResponseEntity<ErrorResponse> response = post(entity, ErrorResponse.class, "/api/passwords/validation");

    List<String> errorMsg = new ArrayList<String>();
    errorMsg.add("A senha deve conter ao menos uma letra maiúscula");

    assertEquals(400, response.getStatusCodeValue());
    assertEquals(errorMsg, response.getBody().getErrors());
  }

  @Test
  void missingSpecialCharacter() {
    PasswordRequest requestPayload = new PasswordRequest("abtp9fokI");

    HttpEntity<PasswordRequest> entity = new HttpEntity<PasswordRequest>(requestPayload, headers);

    ResponseEntity<ErrorResponse> response = post(entity, ErrorResponse.class, "/api/passwords/validation");

    List<String> errorMsg = new ArrayList<String>();
    errorMsg.add("A senha deve conter ao menos um caractere especial");

    assertEquals(400, response.getStatusCodeValue());
    assertEquals(errorMsg, response.getBody().getErrors());
  }

  @Test
  void repeatedCharacter() {
    PasswordRequest requestPayload = new PasswordRequest("abtp9fo!II");

    HttpEntity<PasswordRequest> entity = new HttpEntity<PasswordRequest>(requestPayload, headers);

    ResponseEntity<ErrorResponse> response = post(entity, ErrorResponse.class, "/api/passwords/validation");

    List<String> errorMsg = new ArrayList<String>();
    errorMsg.add("A senha não deve conter caracteres repetidos");

    assertEquals(400, response.getStatusCodeValue());
    assertEquals(errorMsg, response.getBody().getErrors());
  }

  @Test
  void nonSpace() {
    PasswordRequest requestPayload = new PasswordRequest("abtp9fo! kI");

    HttpEntity<PasswordRequest> entity = new HttpEntity<PasswordRequest>(requestPayload, headers);

    ResponseEntity<ErrorResponse> response = post(entity, ErrorResponse.class, "/api/passwords/validation");

    List<String> errorMsg = new ArrayList<String>();
    errorMsg.add("A senha não deve conter espaços");

    assertEquals(400, response.getStatusCodeValue());
    assertEquals(errorMsg, response.getBody().getErrors());
  }

  @Test
  void minimumCharacter() {
    PasswordRequest requestPayload = new PasswordRequest("AbTp9!");

    HttpEntity<PasswordRequest> entity = new HttpEntity<PasswordRequest>(requestPayload, headers);

    ResponseEntity<ErrorResponse> response = post(entity, ErrorResponse.class, "/api/passwords/validation");

    List<String> errorMsg = new ArrayList<String>();
    errorMsg.add("A senha deve conter ao menos 9 caracteres");

    assertEquals(400, response.getStatusCodeValue());
    assertEquals(errorMsg, response.getBody().getErrors());
  }

  @Test
  void onceNumber() {
    PasswordRequest requestPayload = new PasswordRequest("AbTp!fokinmz");

    HttpEntity<PasswordRequest> entity = new HttpEntity<PasswordRequest>(requestPayload, headers);

    ResponseEntity<ErrorResponse> response = post(entity, ErrorResponse.class, "/api/passwords/validation");

    List<String> errorMsg = new ArrayList<String>();
    errorMsg.add("A senha deve conter ao menos um digito");

    assertEquals(400, response.getStatusCodeValue());
    assertEquals(errorMsg, response.getBody().getErrors());
  }

  private ResponseEntity post(HttpEntity entity, Class<?> responseType, String url) {
    return restTemplate.exchange(
      createURLWithPort(url),
      HttpMethod.POST, entity, responseType
    );
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }
}
