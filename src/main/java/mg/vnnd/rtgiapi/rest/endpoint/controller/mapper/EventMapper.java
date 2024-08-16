package mg.vnnd.rtgiapi.rest.endpoint.controller.mapper;

import mg.vnnd.rtgiapi.endpoint.rest.model.CrupdateEvent;
import mg.vnnd.rtgiapi.endpoint.rest.model.EventModel;
import mg.vnnd.rtgiapi.endpoint.rest.model.Location;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
  public mg.vnnd.rtgiapi.rest.endpoint.repository.model.EventModel toDomain(CrupdateEvent event) {
    Location location = event.getLocation();
    return mg.vnnd.rtgiapi.rest.endpoint.repository.model.EventModel.builder()
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

  public EventModel toRest(mg.vnnd.rtgiapi.rest.endpoint.repository.model.EventModel eventModel) {
    var location =
        new Location()
            .name(eventModel.getLocationName())
            .longitude(eventModel.getLongitude())
            .latitude(eventModel.getLatitude())
            .address(eventModel.getAddress());
    return new EventModel()
        .id(eventModel.getId())
        .beginDatetime(eventModel.getBeginDatetime())
        .endDatetime(eventModel.getEndDatetime())
        .eventType(eventModel.getType())
        .greenSpaceId(eventModel.getGreenSpaceId())
        .name(eventModel.getName())
        .meetingFee(eventModel.getMeetingFee())
        .location(location);
  }
}
