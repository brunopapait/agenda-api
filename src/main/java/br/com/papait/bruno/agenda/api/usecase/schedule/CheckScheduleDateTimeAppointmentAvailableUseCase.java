package br.com.papait.bruno.agenda.api.usecase.schedule;

import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTO;
import br.com.papait.bruno.agenda.api.domain.entity.ScheduleEntity;
import br.com.papait.bruno.agenda.api.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckScheduleDateTimeAppointmentAvailableUseCase {

  private final ScheduleRepository scheduleRepository;

  public ScheduleEntity execute(ScheduleDTO dto) {
    var optionalSchedule = this.scheduleRepository.findByDhTime(dto.getDhTime());

    return optionalSchedule.orElse(null);
  }
}
