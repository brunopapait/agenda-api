package br.com.papait.bruno.agenda.api.usecase.schedule;

import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTO;
import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTOResponse;
import br.com.papait.bruno.agenda.api.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class UpdateScheduleUseCase {

  private final ScheduleRepository scheduleRepository;
  private final SaveScheduleUseCase saveScheduleUseCase;

  public ScheduleDTOResponse execute(ScheduleDTO scheduleDTO) {
    this.scheduleRepository.findById(scheduleDTO.getId())
        .orElseThrow(() -> new EntityNotFoundException("Schedule not found"));

    return this.saveScheduleUseCase.execute(scheduleDTO);
  }
}

