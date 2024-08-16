package mg.vnnd.rtgiapi.rest.endpoint.repository;

import java.util.List;
import mg.vnnd.rtgiapi.rest.endpoint.repository.model.GreenSpace;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreenSpaceRepository extends JpaRepository<GreenSpace, String> {
  List<GreenSpace> findAllByUserId(String userId, Pageable pageable);
}
