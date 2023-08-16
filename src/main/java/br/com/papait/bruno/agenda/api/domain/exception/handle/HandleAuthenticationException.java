package br.com.papait.bruno.agenda.api.domain.exception.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleAuthenticationException {
  @ExceptionHandler(AuthenticationServiceException.class)
  public ResponseEntity<String> handleAuthenticationException(AuthenticationServiceException e) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
  }
}
