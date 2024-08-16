package mg.vnnd.rtgiapi.rest.endpoint.repository;

import java.util.List;
import mg.vnnd.rtgiapi.rest.endpoint.repository.model.Animal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, String> {
  List<Animal> findAllByGreenSpaceId(String greenSpaceId, Pageable pageable);

  List<Animal> findAllByGreenSpaceIdAndEndemic(String greenSpaceId, boolean isEndemic, Pageable pageable);
}
