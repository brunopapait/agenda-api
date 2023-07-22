package br.com.papait.bruno.agenda.api.usecase.schedule;

import br.com.papait.bruno.agenda.api.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateScheduleUseCase {

      private final ScheduleRepository agendaRepository;
      public void execute() {
      }
}
