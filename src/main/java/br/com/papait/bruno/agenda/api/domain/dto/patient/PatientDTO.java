package br.com.papait.bruno.agenda.api.domain.dto.patient;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PatientDTO {
  private Long id;
  private String name;
  private String surname;
  private String email;
  private String cpf;
}
