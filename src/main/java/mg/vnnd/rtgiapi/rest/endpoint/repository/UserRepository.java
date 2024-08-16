package mg.vnnd.rtgiapi.rest.endpoint.repository;

import java.util.Optional;
import mg.vnnd.rtgiapi.rest.endpoint.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByEmail(String email);
}
