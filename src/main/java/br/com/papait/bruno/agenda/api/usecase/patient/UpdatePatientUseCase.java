package br.com.papait.bruno.agenda.api.usecase.patient;

import br.com.papait.bruno.agenda.api.domain.dto.patient.PatientDTO;
import br.com.papait.bruno.agenda.api.domain.dto.patient.PatientDTOResponse;
import br.com.papait.bruno.agenda.api.domain.dto.patient.UpdatePatientDTORequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdatePatientUseCase {
  private final FindPatientByIdUseCase findPatientByIdUseCase;
  private final SavePatientUseCase savePatientUseCase;
  private final ModelMapper modelMapper;

  public PatientDTOResponse execute(Long patientId, UpdatePatientDTORequest updatePatientDTORequest) {
    this.findPatientByIdUseCase.execute(patientId);

    updatePatientDTORequest.setId(patientId);
    var patientDTO = this.modelMapper.map(updatePatientDTORequest, PatientDTO.class);
    return this.savePatientUseCase.execute(patientDTO);
  }
}
