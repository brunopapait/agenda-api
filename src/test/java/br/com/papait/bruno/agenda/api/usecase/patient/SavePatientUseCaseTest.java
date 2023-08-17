package br.com.papait.bruno.agenda.api.usecase.patient;

import br.com.papait.bruno.agenda.api.domain.dto.patient.PatientDTO;
import br.com.papait.bruno.agenda.api.domain.entity.PatientEntity;
import br.com.papait.bruno.agenda.api.domain.exception.CpfAlreadyInUseException;
import br.com.papait.bruno.agenda.api.domain.exception.EmailAlreadyInUseException;
import br.com.papait.bruno.agenda.api.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class SavePatientUseCaseTest {

  @InjectMocks
  private SavePatientUseCase savePatientUseCase;

  @Mock
  private FindPatientByCpfUseCase findPatientByCpfUseCase;

  @Mock
  private FindPatientByEmailUseCase findPatientByEmailUseCase;

  @Mock
  private ModelMapper modelMapper;

  @Mock
  private PatientRepository patientRepository;

  @Test
  @DisplayName("Deve salvar o paciente com sucesso")
  void shouldSavePatientSuccessfully() {
    // given
    var patientDto = new PatientDTO(null, "Bruno", "Papait", "bruno@gmail.com", "82356853960");

    // when
    Mockito.when(this.findPatientByCpfUseCase.execute(patientDto.getCpf())).thenReturn(null);
    Mockito.when(this.findPatientByEmailUseCase.execute(patientDto.getEmail())).thenReturn(null);

    var patientEntity = new PatientEntity(null, patientDto.getName(), patientDto.getSurname(), patientDto.getEmail(), patientDto.getCpf());
    Mockito.when(this.modelMapper.map(patientDto, PatientEntity.class)).thenReturn(patientEntity);

    patientEntity.setId(1L);
    Mockito.when(this.patientRepository.save(patientEntity)).thenReturn(patientEntity);

    this.savePatientUseCase.execute(patientDto);

    // then
    Mockito.verify(this.findPatientByCpfUseCase).execute(patientDto.getCpf());
    Mockito.verify(this.findPatientByEmailUseCase).execute(patientDto.getEmail());
    Mockito.verify(this.patientRepository).save(patientEntity);

    Assertions.assertEquals(1L, patientEntity.getId());
  }

  @Test
  @DisplayName("Deve dar erro salvar o paciente com CPF já existente")
  void shouldThrowExceptionOnSavePatientWithExistingCPF() {
    var patientDto = new PatientDTO(null, "Bruno", "Papait", "bruno@gmail.com", "82356853960");
    Mockito.when(this.findPatientByCpfUseCase.execute(patientDto.getCpf())).thenReturn(new PatientDTO());
    var cpfAlreadyInUseException = Assertions.assertThrows(CpfAlreadyInUseException.class, () -> {
      this.savePatientUseCase.execute(patientDto);
    });

    Mockito.verify(this.findPatientByCpfUseCase).execute(patientDto.getCpf());
    Assertions.assertEquals(cpfAlreadyInUseException.getMessage(), "Já existe um paciente com o CPF informado");
  }

  @Test
  @DisplayName("Deve dar erro salvar o paciente com CPF já existente")
  void shouldThrowExceptionOnSavePatientWithExistingEmail() {
    var patientDto = new PatientDTO(null, "Bruno", "Papait", "bruno@gmail.com", "82356853960");
    Mockito.when(this.findPatientByEmailUseCase.execute(patientDto.getEmail())).thenReturn(new PatientDTO());

    var emailAlreadyInUseException = Assertions.assertThrows(EmailAlreadyInUseException.class, () -> {
      this.savePatientUseCase.execute(patientDto);
    });

    Mockito.verify(this.findPatientByEmailUseCase).execute(patientDto.getEmail());
    Assertions.assertEquals(emailAlreadyInUseException.getMessage(), "Já existe um paciente com o e-mail informado");
  }
}
