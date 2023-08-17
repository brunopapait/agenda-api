package br.com.papait.bruno.agenda.api.usecase.schedule;

import br.com.papait.bruno.agenda.api.domain.dto.patient.PatientDTOResponse;
import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTO;
import br.com.papait.bruno.agenda.api.domain.entity.PatientEntity;
import br.com.papait.bruno.agenda.api.domain.entity.ScheduleEntity;
import br.com.papait.bruno.agenda.api.domain.exception.ScheduleDateTimeAppointmentNotAvailableException;
import br.com.papait.bruno.agenda.api.repository.ScheduleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

/**
 * Created by Bruno on 02/11/2016.
 */

@ExtendWith(MockitoExtension.class)
public class SaveScheduleUseCaseTest {

  @InjectMocks
  SaveScheduleUseCase saveScheduleUseCase;

  @Mock
  CheckScheduleDateTimeAppointmentAvailableUseCase checkScheduleDateTimeAppointmentAvailableUseCase;
  @Mock
  ScheduleRepository agendaRepository;

  @Mock
  ModelMapper modelMapper;

  @Captor
  ArgumentCaptor<ScheduleEntity> scheduleCaptor;

  @Test
  @DisplayName("Deve salvar o agendamento com sucesso")
  void shouldSaveScheduleSucessfully() {
    // setup
    // arrange
    var patientDto = new PatientDTOResponse();
    patientDto.setId(1L);
    patientDto.setName("Bruno");

    var scheduleDto = new ScheduleDTO(null, "Descrição", LocalDateTime.now(), patientDto);

    Mockito.when(this.checkScheduleDateTimeAppointmentAvailableUseCase.execute(scheduleDto))
        .thenReturn(null);

    var patientEntity = new PatientEntity();
    patientEntity.setId(1L);

    var scheduleEntity = new ScheduleEntity();
    scheduleEntity.setId(null);
    scheduleEntity.setDescription("Descrição");
    scheduleEntity.setDhTime(LocalDateTime.now());
    scheduleEntity.setPatientEntity(patientEntity);

    Mockito.when(this.modelMapper.map(scheduleDto, ScheduleEntity.class))
        .thenReturn(scheduleEntity);

    scheduleEntity.setId(1L);
    Mockito.when(this.agendaRepository.save(scheduleEntity)).thenReturn(scheduleEntity);

    // teste
    // action
    this.saveScheduleUseCase.execute(scheduleDto);

    // validações
    // assertions
    Mockito.verify(this.checkScheduleDateTimeAppointmentAvailableUseCase).execute(scheduleDto);
    Mockito.verify(this.modelMapper).map(scheduleDto, ScheduleEntity.class);
    Mockito.verify(this.agendaRepository).save(this.scheduleCaptor.capture());

    var scheduleEntityCaptor = this.scheduleCaptor.getValue();
    Assertions.assertEquals(1L, scheduleEntityCaptor.getPatientEntity().getId());
    Assertions.assertEquals(1L, scheduleEntityCaptor.getId());
    Assertions.assertNotNull(scheduleEntityCaptor.getDhCreation());
    Assertions.assertNotNull(scheduleEntityCaptor.getPatientEntity());
  }

  @Test
  @DisplayName("Deve lançar exceção ao tentar salvar um agendamento com horário indisponível")
  void testScheduleDateTimeAppointmentNotAvailableExceptionThrown() {
    var patientDto = new PatientDTOResponse();
    patientDto.setId(1L);
    patientDto.setName("Bruno");

    var scheduleDto = new ScheduleDTO(null, "Descrição", LocalDateTime.now(), patientDto);

    Mockito.when(this.checkScheduleDateTimeAppointmentAvailableUseCase.execute(scheduleDto))
        .thenReturn(new ScheduleEntity());

    var scheduleDateTimeAppointmentNotAvailableException = Assertions.assertThrows(ScheduleDateTimeAppointmentNotAvailableException.class, () -> {
      this.saveScheduleUseCase.execute(scheduleDto);
    });

    Assertions.assertEquals(scheduleDateTimeAppointmentNotAvailableException.getMessage(), "Date time appointment not available");
  }
}
