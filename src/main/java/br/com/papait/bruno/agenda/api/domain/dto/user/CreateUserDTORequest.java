package br.com.papait.bruno.agenda.api.domain.dto.user;

import javax.validation.constraints.NotBlank;

public class CreateUserDTORequest {
  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "Username is required")
  private String username;

  @NotBlank(message = "Password is required")
  private String password;
}
