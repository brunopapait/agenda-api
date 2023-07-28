package br.com.papait.bruno.agenda.api.domain.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
  private Long id;
  private String name;
  private String surname;
  private String email;
  private String cpf;
}
