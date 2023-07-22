package br.com.papait.bruno.agenda.api.domain.dto.patient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTO {
  private Long id;
  private String name;
  private String surname;
  private String email;
  private String cpf;
}
