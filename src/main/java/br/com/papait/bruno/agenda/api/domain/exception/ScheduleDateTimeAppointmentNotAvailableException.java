package br.com.papait.bruno.agenda.api.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ScheduleDateTimeAppointmentNotAvailableException extends RuntimeException {
  public ScheduleDateTimeAppointmentNotAvailableException(String message) {
    super(message);
  }
}
