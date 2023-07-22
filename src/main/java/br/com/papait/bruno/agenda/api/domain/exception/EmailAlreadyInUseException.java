package br.com.papait.bruno.agenda.api.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class EmailAlreadyInUseException extends RuntimeException {
  public EmailAlreadyInUseException(String message) {
    super(message);
  }
}