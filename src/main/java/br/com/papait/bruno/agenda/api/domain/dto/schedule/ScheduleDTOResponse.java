package br.com.papait.bruno.agenda.api.domain.dto.schedule;

import br.com.papait.bruno.agenda.api.domain.dto.patient.PatientDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleDTOResponse {
  private Long id;
  private String description;
  private LocalDateTime dhTime;
  private LocalDateTime dhCreation;
  private PatientDTO patientDTO;
}
