package br.com.papait.bruno.agenda.api.usecase.user;

import br.com.papait.bruno.agenda.api.domain.dto.user.UserDTO;
import br.com.papait.bruno.agenda.api.domain.dto.user.UserDTOResponse;
import br.com.papait.bruno.agenda.api.domain.entity.UserEntity;
import br.com.papait.bruno.agenda.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserUseCase {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final ModelMapper modelMapper;

  public UserDTOResponse execute(UserDTO userDTO) {
    // Validar se nao existe nenhum username no banco de dados igual o username vindo do DTO

    userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    var userEntity = this.modelMapper.map(userDTO, UserEntity.class);
    userRepository.save(userEntity);
    return this.modelMapper.map(userEntity, UserDTOResponse.class);
  }
}
