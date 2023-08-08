package br.com.papait.bruno.agenda.api.api.controller;

import br.com.papait.bruno.agenda.api.domain.dto.schedule.CreateScheculeDTORequest;
import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTO;
import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTOResponse;
import br.com.papait.bruno.agenda.api.usecase.patient.FindPatientByIdUseCase;
import br.com.papait.bruno.agenda.api.usecase.schedule.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

  private final SaveScheduleUseCase createScheduleUseCase;
  private final FindPatientByIdUseCase findPatientByIdUseCase;
  private final FindAllScheduleUseCase findAllScheduleUseCase;
  private final FindByIdScheduleUseCase findByIdScheduleUseCase;
  private final DeleteScheduleByIdUseCase deleteScheduleByIdUseCase;
  private final UpdateScheduleUseCase updateScheduleUseCase;
  private final ModelMapper modelMapper;

  @PostMapping
  @Transactional
  public ResponseEntity<ScheduleDTOResponse> createSchedule(@RequestBody @Valid CreateScheculeDTORequest createScheculeDTORequest) {
    var patientDto = this.findPatientByIdUseCase.execute(createScheculeDTORequest.getPatientId());

    var scheduleDto = this.modelMapper.map(createScheculeDTORequest, ScheduleDTO.class);
    scheduleDto.setPatientDTO(patientDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(this.createScheduleUseCase.execute(scheduleDto));
  }

  @GetMapping
  public ResponseEntity<List<ScheduleDTOResponse>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(this.findAllScheduleUseCase.execute());
  }

  @GetMapping("/{scheduleId}")
  public ResponseEntity<ScheduleDTOResponse> findById(@PathVariable Long scheduleId) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.findByIdScheduleUseCase.execute(scheduleId));
  }

  @DeleteMapping("/{scheduleId}")
  @Transactional
  public ResponseEntity<Void> createSchedule(@PathVariable Long scheduleId) {
    this.deleteScheduleByIdUseCase.execute(scheduleId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PutMapping("{scheduleId}")
  @Transactional
  public ResponseEntity<ScheduleDTOResponse> updateSchedule(
      @PathVariable Long scheduleId,
      @RequestBody @Valid CreateScheculeDTORequest createScheculeDTORequest) {
    var patientDto = this.findPatientByIdUseCase.execute(createScheculeDTORequest.getPatientId());

    var scheduleDto = this.modelMapper.map(createScheculeDTORequest, ScheduleDTO.class);
    scheduleDto.setId(scheduleId);
    scheduleDto.setPatientDTO(patientDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(this.updateScheduleUseCase.execute(scheduleDto));
  }
}
