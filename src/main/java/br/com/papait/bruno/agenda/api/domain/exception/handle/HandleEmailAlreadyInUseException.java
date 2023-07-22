package br.com.papait.bruno.agenda.api.domain.exception.handle;

import br.com.papait.bruno.agenda.api.domain.exception.EmailAlreadyInUseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleEmailAlreadyInUseException {
  @ExceptionHandler(EmailAlreadyInUseException.class)
  public ResponseEntity<String> handleEmailAlreadyInUseException(EmailAlreadyInUseException e) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
  }
}
