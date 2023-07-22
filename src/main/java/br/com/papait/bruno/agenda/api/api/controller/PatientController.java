package br.com.papait.bruno.agenda.api.api.controller;

import br.com.papait.bruno.agenda.api.domain.dto.patient.CreatePatientDTORequest;
import br.com.papait.bruno.agenda.api.domain.dto.patient.PatientDTOResponse;
import br.com.papait.bruno.agenda.api.usecase.patient.CreatePatientUseCase;
import br.com.papait.bruno.agenda.api.usecase.patient.FindAllPatientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

  private final CreatePatientUseCase createPatientUseCase;
  private final FindAllPatientUseCase findAllPatientUseCase;

  @PostMapping
  @Transactional
  public ResponseEntity<PatientDTOResponse> create(
      @Valid @RequestBody CreatePatientDTORequest createPatientDTORequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.createPatientUseCase.execute(createPatientDTORequest));
  }

  @GetMapping
  public ResponseEntity<List<PatientDTOResponse>> findAll() {
    return ResponseEntity.ok(this.findAllPatientUseCase.execute());
  }
}
