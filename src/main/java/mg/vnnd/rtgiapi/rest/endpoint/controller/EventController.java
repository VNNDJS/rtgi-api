package mg.vnnd.rtgiapi.rest.endpoint.controller;

import lombok.AllArgsConstructor;
import mg.vnnd.rtgiapi.endpoint.rest.model.CrupdateEvent;
import mg.vnnd.rtgiapi.endpoint.rest.model.EventModel;
import mg.vnnd.rtgiapi.endpoint.rest.model.GetEvents200Response;
import mg.vnnd.rtgiapi.model.BoundedPageSize;
import mg.vnnd.rtgiapi.model.PageFromOne;
import mg.vnnd.rtgiapi.rest.endpoint.controller.mapper.EventMapper;
import mg.vnnd.rtgiapi.rest.endpoint.service.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EventController {
  private final EventService service;
  private final EventMapper mapper;

  @GetMapping("/events")
  public GetEvents200Response getEvents(
      @RequestParam(value = "green_space_id", required = false) String greenSpaceId,
      @RequestParam(required = false) PageFromOne page,
      @RequestParam(value = "page_size", required = false) BoundedPageSize pageSize) {
    var pagedData = service.getAll(greenSpaceId, page, pageSize);
    return new GetEvents200Response()
        .pageNumber(page.getValue())
        .count(pagedData.count())
        .hasPrevious(pagedData.hasPrevious())
        .data(pagedData.data().stream().map(mapper::toRest).toList())
        .pageSize(pageSize.getValue());
  }

  @GetMapping("/events/{event_id}")
  public EventModel getEvent(@PathVariable("event_id") String eventId) {
    return mapper.toRest(service.getById(eventId));
  }

  @PutMapping("/events/{event_id}")
  public EventModel crupdate(
      @PathVariable("event_id") String eventId, @RequestBody CrupdateEvent crupdateEvent) {
    return mapper.toRest(service.save(eventId, mapper.toDomain(crupdateEvent)));
  }
}
