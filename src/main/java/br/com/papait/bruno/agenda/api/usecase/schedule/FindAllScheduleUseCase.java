package br.com.papait.bruno.agenda.api.usecase.schedule;

import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTOResponse;
import br.com.papait.bruno.agenda.api.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllScheduleUseCase {
  private final ScheduleRepository scheduleRepository;
  private final ModelMapper modelMapper;

  public List<ScheduleDTOResponse> execute() {
    var scheduleEntities = this.scheduleRepository.findAll();

    return scheduleEntities
        .stream()
        .map(scheduleEntity -> this.modelMapper.map(scheduleEntity, ScheduleDTOResponse.class))
        .collect(Collectors.toList());
  }
}
