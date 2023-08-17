package br.com.papait.bruno.agenda.api.usecase.user;

import br.com.papait.bruno.agenda.api.domain.dto.user.UserDTOResponse;
import br.com.papait.bruno.agenda.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllUsersUseCase {

  private final UserRepository userRepository;
  private final ModelMapper modelMapper;

  public List<UserDTOResponse> execute() {
    return this.userRepository.findAll()
        .stream()
        .map(userEntity -> this.modelMapper.map(userEntity, UserDTOResponse.class))
        .collect(Collectors.toList());
  }
}
