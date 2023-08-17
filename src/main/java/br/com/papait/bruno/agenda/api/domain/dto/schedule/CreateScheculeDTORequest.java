package br.com.papait.bruno.agenda.api.domain.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateScheculeDTORequest {
  private Long id;

  @NotBlank(message = "Description is required")
  private String description;

  @NotNull(message = "Date and time of event is required")
  private LocalDateTime dhTime;

  @NotNull(message = "Patient id is required")
  private Long patientId;
}
