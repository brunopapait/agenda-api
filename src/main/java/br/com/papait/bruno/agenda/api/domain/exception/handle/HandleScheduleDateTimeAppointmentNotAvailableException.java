package br.com.papait.bruno.agenda.api.domain.exception.handle;

import br.com.papait.bruno.agenda.api.domain.exception.ScheduleDateTimeAppointmentNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleScheduleDateTimeAppointmentNotAvailableException {
  @ExceptionHandler(ScheduleDateTimeAppointmentNotAvailableException.class)
  public ResponseEntity<String> handleScheduleDateTimeAppointmentNotAvailableException(ScheduleDateTimeAppointmentNotAvailableException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }
}
