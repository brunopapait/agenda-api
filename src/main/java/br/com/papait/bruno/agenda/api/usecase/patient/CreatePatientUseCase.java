package br.com.papait.bruno.agenda.api.usecase.patient;

import br.com.papait.bruno.agenda.api.domain.dto.patient.CreatePatientDTORequest;
import br.com.papait.bruno.agenda.api.domain.dto.patient.PatientDTOResponse;
import br.com.papait.bruno.agenda.api.domain.entity.PatientEntity;
import br.com.papait.bruno.agenda.api.domain.exception.CpfAlreadyInUseException;
import br.com.papait.bruno.agenda.api.domain.exception.EmailAlreadyInUseException;
import br.com.papait.bruno.agenda.api.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CreatePatientUseCase {

  private final FindPatientByCpfUseCase findPatientByCpfUseCase;
  private final FindPatientByEmailUseCase findPatientByEmailUseCase;
  private final ModelMapper modelMapper;
  private final PatientRepository patientRepository;

  public PatientDTOResponse execute(CreatePatientDTORequest createPatientDTORequest) {
    var alreadyExistsPatientWithCpf = this.findPatientByCpfUseCase.execute(createPatientDTORequest.getCpf());
    if (Objects.nonNull(alreadyExistsPatientWithCpf)) {
      throw new CpfAlreadyInUseException("Já existe um paciente com o CPF informado");
    }

    var alreadyExistsPatientWithEmail = this.findPatientByEmailUseCase.execute(createPatientDTORequest.getEmail());
    if (Objects.nonNull(alreadyExistsPatientWithEmail)) {
      throw new EmailAlreadyInUseException("Já existe um paciente com o e-mail informado");
    }

    var patientEntity = this.modelMapper.map(createPatientDTORequest, PatientEntity.class);
    patientEntity = this.patientRepository.save(patientEntity);

    return this.modelMapper.map(patientEntity, PatientDTOResponse.class);
  }
}
