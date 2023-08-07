package br.com.papait.bruno.agenda.api.domain.exception.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleHttpMessageNotReadableException {
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
    String errorMessage = "Ocorreu um erro na requisição. Verifique se os dados estão corretos e tente novamente.";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
  }
}
