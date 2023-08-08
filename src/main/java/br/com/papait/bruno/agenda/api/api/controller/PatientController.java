package br.com.papait.bruno.agenda.api.api.controller;

import br.com.papait.bruno.agenda.api.domain.dto.patient.CreatePatientDTORequest;
import br.com.papait.bruno.agenda.api.domain.dto.patient.PatientDTO;
import br.com.papait.bruno.agenda.api.domain.dto.patient.PatientDTOResponse;
import br.com.papait.bruno.agenda.api.domain.dto.patient.UpdatePatientDTORequest;
import br.com.papait.bruno.agenda.api.usecase.patient.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

  private final SavePatientUseCase savePatientUseCase;
  private final FindAllPatientUseCase findAllPatientUseCase;
  private final FindPatientByIdUseCase findPatientByIdUseCase;
  private final DeletePatientByIdUseCase deletePatientByIdUseCase;
  private final UpdatePatientUseCase updatePatientUseCase;
  private final ModelMapper modelMapper;

  @PostMapping
  @Transactional
  public ResponseEntity<PatientDTOResponse> create(
      @Valid @RequestBody CreatePatientDTORequest createPatientDTORequest) {
    var patientDto = this.modelMapper.map(createPatientDTORequest, PatientDTO.class);
    return ResponseEntity.status(HttpStatus.CREATED).body(this.savePatientUseCase.execute(patientDto));
  }

  @GetMapping
  public ResponseEntity<List<PatientDTOResponse>> findAll() {
    return ResponseEntity.ok(this.findAllPatientUseCase.execute());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PatientDTOResponse> findById(@PathVariable Long id) {
    return ResponseEntity.ok(this.findPatientByIdUseCase.execute(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    this.deletePatientByIdUseCase.execute(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<PatientDTOResponse> update(@PathVariable Long id, @Valid @RequestBody UpdatePatientDTORequest updatePatientDTORequest) {
    return ResponseEntity.ok(this.updatePatientUseCase.execute(id, updatePatientDTORequest));
  }
}
