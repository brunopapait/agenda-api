package br.com.papait.bruno.agenda.api.usecase.schedule;

import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTOResponse;
import br.com.papait.bruno.agenda.api.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class FindByIdScheduleUseCase {

  private final ModelMapper modelMapper;
  private final ScheduleRepository scheduleRepository;

  public ScheduleDTOResponse execute(Long scheduleId) {
    var scheduleEntity = this.scheduleRepository.findById(scheduleId)
        .orElseThrow(() -> new EntityNotFoundException("Schedule not found"));

    return this.modelMapper.map(scheduleEntity, ScheduleDTOResponse.class);
  }
}
