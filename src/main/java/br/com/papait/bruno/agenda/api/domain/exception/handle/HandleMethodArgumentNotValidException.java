package br.com.papait.bruno.agenda.api.domain.exception.handle;

import br.com.papait.bruno.agenda.api.domain.dto.error.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class HandleMethodArgumentNotValidException {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseDTO> handle(MethodArgumentNotValidException ex) {
    String message = "Erros de validação";
    List<String> errors = new ArrayList<>();

    ex.getBindingResult().getFieldErrors().forEach(error -> {
      errors.add(error.getDefaultMessage());
    });

    var errorDTO = new ErrorResponseDTO(message, errors);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
  }
}
