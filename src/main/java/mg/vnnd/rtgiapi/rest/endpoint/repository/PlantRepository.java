package mg.vnnd.rtgiapi.rest.endpoint.repository;

import java.util.List;
import mg.vnnd.rtgiapi.rest.endpoint.repository.model.Animal;
import mg.vnnd.rtgiapi.rest.endpoint.repository.model.Plant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant, String> {
  List<Plant> findAllByGreenSpaceId(String greenSpaceId, Pageable pageable);

  List<Plant> findAllByGreenSpaceIdAndEndemic(
      String greenSpaceId, boolean isEndemic, Pageable pageable);
}
