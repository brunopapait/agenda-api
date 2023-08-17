package br.com.papait.bruno.agenda.api.domain.dto.schedule;

import br.com.papait.bruno.agenda.api.domain.dto.patient.PatientDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
  private Long id;
  private String description;
  private LocalDateTime dhTime;
  private PatientDTO patientDTO;
}
