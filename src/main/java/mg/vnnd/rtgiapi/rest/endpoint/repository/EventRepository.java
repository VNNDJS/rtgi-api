package mg.vnnd.rtgiapi.rest.endpoint.repository;

import java.util.List;
import mg.vnnd.rtgiapi.rest.endpoint.repository.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventModel, String> {
  List<EventModel> findAllByGreenSpaceId(String greenSpaceId);
}
