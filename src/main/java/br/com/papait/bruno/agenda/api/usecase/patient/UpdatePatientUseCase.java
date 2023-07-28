package br.com.papait.bruno.agenda.api.usecase.patient;

import br.com.papait.bruno.agenda.api.domain.dto.patient.PatientDTOResponse;
import br.com.papait.bruno.agenda.api.domain.dto.patient.UpdatePatientDTORequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdatePatientUseCase {
  private final FindPatientByIdUseCase findPatientByIdUseCase;
  private final SavePatientUseCase savePatientUseCase;

  public PatientDTOResponse execute(Long patientId, UpdatePatientDTORequest updatePatientDTORequest) {
    this.findPatientByIdUseCase.execute(patientId);

    updatePatientDTORequest.setId(patientId);
    return this.savePatientUseCase.execute(updatePatientDTORequest);
  }
}
