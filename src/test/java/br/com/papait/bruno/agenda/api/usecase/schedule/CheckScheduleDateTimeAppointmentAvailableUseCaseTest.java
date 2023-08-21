package br.com.papait.bruno.agenda.api.usecase.schedule;

import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTO;
import br.com.papait.bruno.agenda.api.domain.entity.ScheduleEntity;
import br.com.papait.bruno.agenda.api.repository.ScheduleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CheckScheduleDateTimeAppointmentAvailableUseCaseTest {

  @InjectMocks
  private CheckScheduleDateTimeAppointmentAvailableUseCase checkScheduleDateTimeAppointmentAvailableUseCase;

  @Mock
  private ScheduleRepository scheduleRepository;

  @Test
  @DisplayName("Deve retornar o agendamento quando o agendamento estiver disponível na data/hora informada")
  public void shouldReturnScheduleWhenScheduleIsAvailable() {
    var scheduleDto = new ScheduleDTO();
    scheduleDto.setId(1L);
    scheduleDto.setDhTime(LocalDateTime.now());

    var scheduleEntity = new ScheduleEntity();
    scheduleEntity.setDhTime(LocalDateTime.now());
    scheduleEntity.setId(1L);

    Mockito.when(this.scheduleRepository.findByDhTime(scheduleDto.getDhTime())).thenReturn(Optional.of(scheduleEntity));
    this.checkScheduleDateTimeAppointmentAvailableUseCase.execute(scheduleDto);

    Mockito.verify(this.scheduleRepository, Mockito.times(1)).findByDhTime(scheduleDto.getDhTime());
    Assertions.assertEquals(scheduleEntity.getId(), this.checkScheduleDateTimeAppointmentAvailableUseCase.execute(scheduleDto).getId());
  }

  @Test
  @DisplayName("Deve retornar null quando o agendamento não estiver disponível na data/hora informada")
  public void shouldReturnNullWhenScheduleIsNotAvailable() {
    var scheduleDto = new ScheduleDTO();
    scheduleDto.setId(1L);
    scheduleDto.setDhTime(LocalDateTime.now());

    var scheduleEntity = new ScheduleEntity();
    scheduleEntity.setDhTime(LocalDateTime.now());
    scheduleEntity.setId(1L);

    Mockito.when(this.scheduleRepository.findByDhTime(scheduleDto.getDhTime())).thenReturn(Optional.empty());
    this.checkScheduleDateTimeAppointmentAvailableUseCase.execute(scheduleDto);

    Mockito.verify(this.scheduleRepository, Mockito.times(1)).findByDhTime(scheduleDto.getDhTime());
    Assertions.assertNull(this.checkScheduleDateTimeAppointmentAvailableUseCase.execute(scheduleDto));
  }
}
