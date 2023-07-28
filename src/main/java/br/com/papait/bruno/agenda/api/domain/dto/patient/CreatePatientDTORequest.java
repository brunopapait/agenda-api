package br.com.papait.bruno.agenda.api.domain.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePatientDTORequest {

  private Long id;

  @NotBlank(message = "Name is required")
  private String name;
  @NotBlank(message = "Surname is required")
  private String surname;

  @Email(message = "Email is invalid")
  @NotBlank(message = "Email is required")
  private String email;

  @NotBlank(message = "CPF is required")
  @Size(min = 11, max = 11, message = "CPF must be 11 characters")
  private String cpf;
}
