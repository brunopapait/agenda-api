package br.com.papait.bruno.agenda.api.domain.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
  private Long id;
  private String name;
  private String username;
  private String password;
}
