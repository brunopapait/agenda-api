package br.com.papait.bruno.agenda.api.usecase.patient;

import br.com.papait.bruno.agenda.api.domain.dto.patient.PatientDTO;
import br.com.papait.bruno.agenda.api.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindPatientByEmailUseCase {

  private final PatientRepository patientRepository;
  private final ModelMapper modelMapper;

  public PatientDTO execute(String email) {
    var patientEntity = this.patientRepository.findByEmail(email);
    return patientEntity.map(entity -> this.modelMapper.map(entity, PatientDTO.class)).orElse(null);
  }
}
