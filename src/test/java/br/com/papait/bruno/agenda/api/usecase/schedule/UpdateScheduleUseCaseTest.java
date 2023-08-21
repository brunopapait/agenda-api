package br.com.papait.bruno.agenda.api.usecase.schedule;

import br.com.papait.bruno.agenda.api.domain.dto.patient.PatientDTO;
import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTO;
import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTOResponse;
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
public class UpdateScheduleUseCaseTest {

  @InjectMocks
  private UpdateScheduleUseCase updateScheduleUseCase;

  @Mock
  private ScheduleRepository scheduleRepository;

  @Mock
  private SaveScheduleUseCase saveScheduleUseCase;

  @Test
  @DisplayName("Deve atualizar um agendamento com sucesso")
  public void shouldUpdateScheduleSuccessfuly() {
    var patientDTO = new PatientDTO();
    patientDTO.setId(1L);

    var scheduleDTO = new ScheduleDTO();
    scheduleDTO.setId(1L);
    scheduleDTO.setDescription("Descrição");
    scheduleDTO.setDhTime(LocalDateTime.now());
    scheduleDTO.setPatientDTO(patientDTO);

    Mockito.when(this.scheduleRepository.findById(scheduleDTO.getId()))
        .thenReturn(Optional.of(new ScheduleEntity()));

    var scheduleDtoResponse = new ScheduleDTOResponse();
    scheduleDtoResponse.setId(scheduleDTO.getId());
    scheduleDtoResponse.setDescription(scheduleDTO.getDescription());
    scheduleDtoResponse.setDhTime(scheduleDTO.getDhTime());
    scheduleDtoResponse.setPatientDTO(scheduleDTO.getPatientDTO());

    Mockito.when(this.saveScheduleUseCase.execute(scheduleDTO))
        .thenReturn(scheduleDtoResponse);

    scheduleDtoResponse = this.updateScheduleUseCase.execute(scheduleDTO);

    Mockito.verify(this.scheduleRepository).findById(scheduleDTO.getId());
    Mockito.verify(this.saveScheduleUseCase).execute(scheduleDTO);

    Mockito.verifyNoMoreInteractions(this.scheduleRepository);
    Mockito.verifyNoMoreInteractions(this.saveScheduleUseCase);

    Assertions.assertEquals(scheduleDTO.getId(), scheduleDtoResponse.getId());
  }

  @Test
  @DisplayName("Deve lançar uma excessão ao tentar atualizar um agendamento que o ID não existe")
  public void shouldThrowExceptionThatUpdateSchedule() {
    var patientDTO = new PatientDTO();
    patientDTO.setId(1L);

    var scheduleDTO = new ScheduleDTO();
    scheduleDTO.setId(1L);
    scheduleDTO.setDescription("Descrição");
    scheduleDTO.setDhTime(LocalDateTime.now());
    scheduleDTO.setPatientDTO(patientDTO);

    Mockito.when(this.scheduleRepository.findById(scheduleDTO.getId()))
        .thenReturn(Optional.empty());

    var entityNotFoundException = Assertions.assertThrows(EntityNotFoundException.class, () -> {
      this.updateScheduleUseCase.execute(scheduleDTO);
    });

    Mockito.verify(this.scheduleRepository).findById(scheduleDTO.getId());
    Mockito.verifyNoMoreInteractions(this.scheduleRepository);

    Assertions.assertEquals("Schedule not found", entityNotFoundException.getMessage());
  }
}
