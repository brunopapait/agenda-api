package br.com.papait.bruno.agenda.api.usecase.schedule;

import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTOResponse;
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
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class FindAllScheduleUseCaseTest {

  @InjectMocks
  private FindAllScheduleUseCase findAllScheduleUseCase;
  @Mock
  private ScheduleRepository scheduleRepository;

  @Mock
  private ModelMapper modelMapper;

  @Test
  @DisplayName("Deve listar uma lista vazia de agendamentos com sucesso")
  public void shouldFindAllScheduleEmptySuccessfully() {
    var scheduleEntities = new ArrayList<ScheduleEntity>();
    Mockito.when(this.scheduleRepository.findAll()).thenReturn(scheduleEntities);

    var scheduleDTOResponses = this.findAllScheduleUseCase.execute();

    Mockito.verify(this.scheduleRepository, Mockito.times(1)).findAll();
    Mockito.verifyNoMoreInteractions(this.scheduleRepository);
    Assertions.assertEquals(0L, scheduleDTOResponses.size());
  }

  @Test
  @DisplayName("Deve listar uma lista com 3 agendamentos com sucesso")
  public void shouldFindAllScheduleSize3Successfully() {
    var patientEntity1 = new PatientEntity();
    patientEntity1.setId(1L);

    var scheduleEntity1 = new ScheduleEntity();
    scheduleEntity1.setId(1L);
    scheduleEntity1.setPatientEntity(patientEntity1);

    var patientEntity2 = new PatientEntity();
    patientEntity2.setId(2L);

    var scheduleEntity2 = new ScheduleEntity();
    scheduleEntity2.setId(2L);
    scheduleEntity2.setPatientEntity(patientEntity2);

    var patientEntity3 = new PatientEntity();
    patientEntity3.setId(3L);

    var scheduleEntity3 = new ScheduleEntity();
    scheduleEntity3.setId(3L);
    scheduleEntity3.setPatientEntity(patientEntity3);

    var scheduleEntities = new ArrayList<ScheduleEntity>();
    scheduleEntities.add(scheduleEntity1);
    scheduleEntities.add(scheduleEntity2);
    scheduleEntities.add(scheduleEntity3);

    Mockito.when(this.scheduleRepository.findAll()).thenReturn(scheduleEntities);

    var scheduleDTOResponses = this.findAllScheduleUseCase.execute();

    Mockito.verify(this.scheduleRepository, Mockito.times(1)).findAll();
    Mockito.verifyNoMoreInteractions(this.scheduleRepository);
    Assertions.assertEquals(3L, scheduleDTOResponses.size());
  }
}
