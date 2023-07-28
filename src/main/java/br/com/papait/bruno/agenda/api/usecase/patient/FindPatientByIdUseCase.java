package br.com.papait.bruno.agenda.api.usecase.patient;

import br.com.papait.bruno.agenda.api.domain.dto.patient.PatientDTOResponse;
import br.com.papait.bruno.agenda.api.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class FindPatientByIdUseCase {

  private final PatientRepository patientRepository;
  private final ModelMapper modelMapper;

  public PatientDTOResponse execute(Long patientId) {
    var patientEntity = this.patientRepository.findById(patientId)
        .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

    return this.modelMapper.map(patientEntity, PatientDTOResponse.class);
  }
}
