package mg.vnnd.rtgiapi.rest.endpoint.service;

import static java.util.Objects.requireNonNull;

import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.AllArgsConstructor;
import mg.vnnd.rtgiapi.endpoint.rest.model.SignUpUser;
import mg.vnnd.rtgiapi.endpoint.rest.model.UpdateUserProfile;
import mg.vnnd.rtgiapi.model.exception.BadRequestException;
import mg.vnnd.rtgiapi.model.exception.NotFoundException;
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
            .type(User.UserTypeEnum.fromRest(requireNonNull(signUpUser.getType())))
            .encodedPassword(passwordEncoder.encode(signUpUser.getPassword()))
            .build());
  }

  public User getById(String userId) {
    return findById(userId)
        .orElseThrow(() -> new NotFoundException("User with id " + userId + " not found"));
  }

  public Optional<User> findById(String userId) {
    return repository.findById(userId);
  }

  public Optional<User> findByEmail(String email) {
    return repository.findByEmail(email);
  }

  public User getByEmail(String email) {
    return findByEmail(email)
        .orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));
  }

  @Transactional
  public User update(String userId, UpdateUserProfile userProfile) {
    User persisted = getById(userId);
    persisted.setEmail(userProfile.getEmail());
    persisted.setBirthdate(userProfile.getBirthdate());
    persisted.setName(userProfile.getName());
    return repository.save(persisted);
  }
}
