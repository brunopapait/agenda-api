package br.com.papait.bruno.agenda.api.usecase.schedule;

import br.com.papait.bruno.agenda.api.domain.entity.PatientEntity;
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

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DeleteScheduleByIdUseCaseTest {

  @InjectMocks
  DeleteScheduleByIdUseCase deleteScheduleByIdUseCase;

  @Mock
  ScheduleRepository scheduleRepository;

  @Test
  @DisplayName("Deve remover um agendamento pelo id com sucesso")
  public void shouldDeleteScheduleByIdSuccess() {
    var scheduleId = 1L;

    var patientEntity = new PatientEntity();
    patientEntity.setId(1L);

    var scheduleEntity = new ScheduleEntity();
    scheduleEntity.setId(scheduleId);
    scheduleEntity.setDescription("Descrição");
    scheduleEntity.setDhTime(LocalDateTime.now());
    scheduleEntity.setPatientEntity(patientEntity);

    Mockito.when(this.scheduleRepository.findById(scheduleId))
        .thenReturn(Optional.of(scheduleEntity));

    this.deleteScheduleByIdUseCase.execute(scheduleId);

    Mockito.verify(this.scheduleRepository).findById(scheduleId);
    Mockito.verify(this.scheduleRepository).delete(scheduleEntity);

    Assertions.assertEquals(scheduleId, scheduleEntity.getId());
  }

  @Test
  @DisplayName("Deve lançar excessão ao tentar remover um agendamento que od ID não existe")
  public void shouldThrowExceptionWhenTryDeleteScheduleByIdThatNotExists() {
    var scheduleId = 1L;

    Mockito.when(this.scheduleRepository.findById(scheduleId))
        .thenReturn(Optional.empty());

    var entityNotFoundException = Assertions.assertThrows(EntityNotFoundException.class, () -> {
      this.deleteScheduleByIdUseCase.execute(scheduleId);
    });

    Mockito.verify(this.scheduleRepository).findById(scheduleId);
    Assertions.assertEquals("Schedule not found", entityNotFoundException.getMessage());
  }
}
