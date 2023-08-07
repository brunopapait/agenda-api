package br.com.papait.bruno.agenda.api.api.controller;

import br.com.papait.bruno.agenda.api.domain.dto.schedule.CreateScheculeDTORequest;
import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTO;
import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTOResponse;
import br.com.papait.bruno.agenda.api.domain.mapper.schedule.ScheduleMapper;
import br.com.papait.bruno.agenda.api.usecase.patient.FindPatientByIdUseCase;
import br.com.papait.bruno.agenda.api.usecase.schedule.CreateScheduleUseCase;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

  private final CreateScheduleUseCase createScheduleUseCase;
  private final FindPatientByIdUseCase findPatientByIdUseCase;
  private final ModelMapper modelMapper;

  @PostMapping
  @Transactional
  public ResponseEntity<ScheduleDTOResponse> createSchedule(@RequestBody @Valid CreateScheculeDTORequest createScheculeDTORequest) {
    var patientDto = this.findPatientByIdUseCase.execute(createScheculeDTORequest.getPatientId());

    var scheduleDto = this.modelMapper.map(createScheculeDTORequest, ScheduleDTO.class);
    scheduleDto.setPatientDTO(patientDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(this.createScheduleUseCase.execute(scheduleDto));
  }
}
