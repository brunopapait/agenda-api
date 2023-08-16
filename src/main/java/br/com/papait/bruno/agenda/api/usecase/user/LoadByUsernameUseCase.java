package br.com.papait.bruno.agenda.api.usecase.user;

import br.com.papait.bruno.agenda.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadByUsernameUseCase {

  private final UserService userService;

  public UserDetails execute(String username) {
    return this.userService.loadUserByUsername(username);
  }
}
