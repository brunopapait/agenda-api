package br.com.papait.bruno.agenda.api.usecase.schedule;

import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTO;
import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTOResponse;
import br.com.papait.bruno.agenda.api.domain.entity.PatientEntity;
import br.com.papait.bruno.agenda.api.domain.entity.ScheduleEntity;
import br.com.papait.bruno.agenda.api.domain.exception.ScheduleDateTimeAppointmentNotAvailableException;
import br.com.papait.bruno.agenda.api.repository.ScheduleRepository;
import br.com.papait.bruno.agenda.api.usecase.patient.FindPatientByIdUseCase;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateScheduleUseCase {

  private final ScheduleRepository agendaRepository;
  private final FindPatientByIdUseCase findPatientByIdUseCase;
  private final CheckScheduleDateTimeAppointmentAvailableUseCase checkScheduleDateTimeAppointmentAvailableUseCase;
  private final ModelMapper modelMapper;

  public ScheduleDTOResponse execute(ScheduleDTO scheduleDTO) {
    // Validar se o paciente existe
    var patientDto = this.findPatientByIdUseCase.execute(scheduleDTO.getPatientDTO().getId());
    scheduleDTO.setPatientDTO(patientDto);

    // Validar se o horário está disponível
    var alreadySchedule = this.checkScheduleDateTimeAppointmentAvailableUseCase.execute(scheduleDTO);
    if (alreadySchedule != null) {
      throw new ScheduleDateTimeAppointmentNotAvailableException("Date time appointment not available");
    }

    var patientEntity = this.modelMapper.map(patientDto, PatientEntity.class);
    var scheduleEntity = this.modelMapper.map(scheduleDTO, ScheduleEntity.class);
    scheduleEntity.setPatientEntity(patientEntity);

    scheduleEntity = this.agendaRepository.save(scheduleEntity);

    var scheduleDtoResponse = this.modelMapper.map(scheduleEntity, ScheduleDTOResponse.class);
    scheduleDtoResponse.setPatientDTO(patientDto);

    return scheduleDtoResponse;
  }
}
