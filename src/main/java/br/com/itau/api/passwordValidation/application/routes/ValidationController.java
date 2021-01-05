package br.com.itau.api.passwordValidation.application.routes;

import br.com.itau.api.passwordValidation.domain.PasswordRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/passwords")
public class ValidationController {

  @PostMapping(value = "/validation")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Boolean> validation(@Valid @RequestBody(required = false) PasswordRequest body) {
    return ResponseEntity.ok(true);
  }

}
