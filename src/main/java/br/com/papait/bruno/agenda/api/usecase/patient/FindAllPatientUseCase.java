package br.com.papait.bruno.agenda.api.usecase.patient;

import br.com.papait.bruno.agenda.api.domain.dto.patient.PatientDTOResponse;
import br.com.papait.bruno.agenda.api.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllPatientUseCase {

  private final PatientRepository patientRepository;
  private final ModelMapper modelMapper;

  public List<PatientDTOResponse> execute() {
    return this.patientRepository.findAll()
        .stream()
        .map(patientEntity -> this.modelMapper.map(patientEntity, PatientDTOResponse.class))
        .collect(Collectors.toList());
  }
}
