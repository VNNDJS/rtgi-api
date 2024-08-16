package mg.vnnd.rtgiapi.rest.endpoint.service;

import static java.util.Objects.requireNonNull;

import lombok.AllArgsConstructor;
import mg.vnnd.rtgiapi.endpoint.rest.model.SignUpUser;
import mg.vnnd.rtgiapi.model.exception.BadRequestException;
import mg.vnnd.rtgiapi.rest.endpoint.repository.model.User;
import mg.vnnd.rtgiapi.rest.endpoint.repository.model.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;

  public User createUser(SignUpUser signUpUser) {
    if (repository.existsById(requireNonNull(signUpUser.getId()))) {
      throw new BadRequestException("User with id " + signUpUser.getId() + " already exists");
    }
    return repository.save(
        User.builder()
            .id(signUpUser.getId())
            .email(signUpUser.getEmail())
            .type(signUpUser.getType())
            .encodedPassword(passwordEncoder.encode(signUpUser.getPassword()))
            .build());
  }
}
