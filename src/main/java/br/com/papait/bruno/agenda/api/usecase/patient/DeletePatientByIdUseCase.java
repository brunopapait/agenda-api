package br.com.papait.bruno.agenda.api.usecase.patient;

import br.com.papait.bruno.agenda.api.domain.entity.PatientEntity;
import br.com.papait.bruno.agenda.api.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletePatientByIdUseCase {

  private final PatientRepository patientRepository;
  private final FindPatientByIdUseCase findPatientByIdUseCase;
  private final ModelMapper modelMapper;

  public void execute(Long id) {
    var patientDTOResponse = this.findPatientByIdUseCase.execute(id);

    var patientEntity = this.modelMapper.map(patientDTOResponse, PatientEntity.class);
    this.patientRepository.delete(patientEntity);
  }
}
