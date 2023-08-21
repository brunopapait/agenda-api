package br.com.papait.bruno.agenda.api.usecase.schedule;

import br.com.papait.bruno.agenda.api.domain.dto.patient.PatientDTO;
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

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FindByIdScheduleUseCaseTest {

  @InjectMocks
  private FindByIdScheduleUseCase findByIdScheduleUseCase;
  @Mock
  private ScheduleRepository scheduleRepository;

  @Mock
  private ModelMapper modelMapper;

  @Test
  @DisplayName("Deve listar um agendamento com sucesso")
  public void shouldFindByIdScheduleSuccessfully() {
    var scheduleId = 1L;

    var patientEntity = new PatientEntity();
    patientEntity.setId(1L);

    var scheduleEntity = new ScheduleEntity();
    scheduleEntity.setId(scheduleId);
    scheduleEntity.setDescription("Teste");
    scheduleEntity.setCreationTime();
    scheduleEntity.setDhTime(LocalDateTime.now());
    scheduleEntity.setPatientEntity(patientEntity);

    Mockito.when(this.scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(scheduleEntity));

    var scheduleDTOResponse = new ScheduleDTOResponse();
    scheduleDTOResponse.setId(scheduleId);
    scheduleDTOResponse.setDescription(scheduleEntity.getDescription());
    scheduleDTOResponse.setDhCreation(scheduleEntity.getDhCreation());
    scheduleDTOResponse.setDhTime(scheduleEntity.getDhTime());

    var patientDto = new PatientDTO();
    patientDto.setId(patientEntity.getId());
    scheduleDTOResponse.setPatientDTO(patientDto);

    Mockito.when(this.modelMapper.map(scheduleEntity, ScheduleDTOResponse.class)).thenReturn(scheduleDTOResponse);
    this.findByIdScheduleUseCase.execute(scheduleId);

    Assertions.assertNotEquals(null, scheduleDTOResponse);
    Assertions.assertEquals(scheduleId, scheduleDTOResponse.getId());
    Assertions.assertEquals(scheduleEntity.getDescription(), scheduleDTOResponse.getDescription());

    Mockito.verify(this.scheduleRepository, Mockito.times(1)).findById(scheduleId);
  }

  @Test
  @DisplayName("Deve lançar EntityNotFoundException quando não encontrar o agendamento")
  public void shouldThrowEntityNotFoundExceptionWhenScheduleNotFound() {
    var scheduleId = 1L;
    Mockito.when(this.scheduleRepository.findById(scheduleId)).thenReturn(Optional.empty());

    EntityNotFoundException entityNotFoundException = Assertions.assertThrows(EntityNotFoundException.class, () -> this.findByIdScheduleUseCase.execute(scheduleId));

    Mockito.verify(this.scheduleRepository, Mockito.times(1)).findById(scheduleId);
    Assertions.assertEquals("Schedule not found", entityNotFoundException.getMessage());
  }
}
