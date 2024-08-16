package mg.vnnd.rtgiapi.rest.endpoint.repository;

import java.util.List;
import mg.vnnd.rtgiapi.rest.endpoint.repository.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
  List<Event> findAllByGreenSpaceId(String greenSpaceId);
}
