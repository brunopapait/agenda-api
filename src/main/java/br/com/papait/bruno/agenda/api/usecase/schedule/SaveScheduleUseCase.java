package br.com.papait.bruno.agenda.api.usecase.schedule;

import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTO;
import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTOResponse;
import br.com.papait.bruno.agenda.api.domain.entity.ScheduleEntity;
import br.com.papait.bruno.agenda.api.domain.exception.ScheduleDateTimeAppointmentNotAvailableException;
import br.com.papait.bruno.agenda.api.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SaveScheduleUseCase {

  private final ScheduleRepository agendaRepository;
  private final CheckScheduleDateTimeAppointmentAvailableUseCase checkScheduleDateTimeAppointmentAvailableUseCase;
  private final ModelMapper modelMapper;

  public ScheduleDTOResponse execute(ScheduleDTO scheduleDTO) {
    // Validar se o horário está disponível
    var alreadySchedule = this.checkScheduleDateTimeAppointmentAvailableUseCase.execute(scheduleDTO);
    if (alreadySchedule != null) {
      throw new ScheduleDateTimeAppointmentNotAvailableException("Date time appointment not available");
    }

    var scheduleEntity = this.modelMapper.map(scheduleDTO, ScheduleEntity.class);

    scheduleEntity = this.agendaRepository.save(scheduleEntity);
    scheduleEntity.setCreationTime();
    return this.modelMapper.map(scheduleEntity, ScheduleDTOResponse.class);
  }
}
