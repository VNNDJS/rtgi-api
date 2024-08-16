package mg.vnnd.rtgiapi.rest.endpoint.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import mg.vnnd.rtgiapi.model.BoundedPageSize;
import mg.vnnd.rtgiapi.model.Page;
import mg.vnnd.rtgiapi.model.PageFromOne;
import mg.vnnd.rtgiapi.model.exception.NotFoundException;
import mg.vnnd.rtgiapi.rest.endpoint.repository.EventRepository;
import mg.vnnd.rtgiapi.rest.endpoint.repository.model.Event;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventService {
  private final EventRepository repository;

  public Page<Event> getAll(String greenSpaceId, PageFromOne page, BoundedPageSize pageSize) {
    var pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue());
    List<Event> data;
    if (greenSpaceId != null) {
      data = repository.findAllByGreenSpaceId(greenSpaceId);
    } else {
      data = repository.findAll(pageable).getContent();
    }
    return new Page<>(page, pageSize, data);
  }

  public Optional<Event> findById(String id) {
    return repository.findById(id);
  }

  public Event getById(String id) {
    return findById(id).orElseThrow(() -> new NotFoundException("Event " + id + "not found."));
  }

  public Event save(String eventId, Event event) {
    return repository.save(event);
  }
}
