package mg.vnnd.rtgiapi.rest.endpoint.controller.mapper;

import mg.vnnd.rtgiapi.endpoint.rest.model.CrupdateEvent;
import mg.vnnd.rtgiapi.endpoint.rest.model.Event;
import mg.vnnd.rtgiapi.endpoint.rest.model.Location;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
  public mg.vnnd.rtgiapi.rest.endpoint.repository.model.Event toDomain(CrupdateEvent event) {
    Location location = event.getLocation();
    return mg.vnnd.rtgiapi.rest.endpoint.repository.model.Event.builder()
        .id(event.getId())
        .beginDatetime(event.getBeginDatetime())
        .endDatetime(event.getEndDatetime())
        .type(event.getEventType())
        .greenSpaceId(event.getGreenSpaceId())
        .name(event.getName())
        .locationName(location.getName())
        .longitude(location.getLongitude())
        .latitude(location.getLatitude())
        .address(location.getAddress())
        .description(event.getDescription())
        .meetingFee(event.getMeetingFee())
        .build();
  }

  public Event toRest(mg.vnnd.rtgiapi.rest.endpoint.repository.model.Event event) {
    var location =
        new Location()
            .name(event.getLocationName())
            .longitude(event.getLongitude())
            .latitude(event.getLatitude())
            .address(event.getAddress());
    return new Event()
        .id(event.getId())
        .beginDatetime(event.getBeginDatetime())
        .endDatetime(event.getEndDatetime())
        .eventType(event.getType())
        .greenSpaceId(event.getGreenSpaceId())
        .name(event.getName())
        .meetingFee(event.getMeetingFee())
        .location(location);
  }
}
