package br.com.papait.bruno.agenda.api.domain.dto.patient;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PatientDTO {
  private Long id;
  private String name;
  private String surname;
  private String email;
  private String cpf;
}
