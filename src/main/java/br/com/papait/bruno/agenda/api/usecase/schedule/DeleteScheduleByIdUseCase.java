package br.com.papait.bruno.agenda.api.usecase.schedule;

import br.com.papait.bruno.agenda.api.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class DeleteScheduleByIdUseCase {

  private final ScheduleRepository scheduleRepository;

  public void execute(Long scheduleId) {
    var scheduleEntity = this.scheduleRepository.findById(scheduleId)
        .orElseThrow(() -> new EntityNotFoundException("Schedule not found"));

    this.scheduleRepository.delete(scheduleEntity);
  }
}
