package mg.vnnd.rtgiapi.rest.endpoint.controller.mapper;

import mg.vnnd.rtgiapi.endpoint.rest.model.GreenSpace;
import mg.vnnd.rtgiapi.endpoint.rest.model.GreenSpaceProfile;
import mg.vnnd.rtgiapi.endpoint.rest.model.Location;
import org.springframework.stereotype.Component;

@Component
public class GreenSpaceMapper {
  public GreenSpace toRest(mg.vnnd.rtgiapi.rest.endpoint.repository.model.GreenSpace domain) {
    GreenSpaceProfile profile =
        new GreenSpaceProfile()
            .area(domain.getArea())
            .description(domain.getDescription())
            .ticketPrice(domain.getTicketPrice())
            .location(
                new Location()
                    .address(domain.getAddress())
                    .longitude(domain.getLongitude())
                    .latitude(domain.getLatitude())
                    .name(domain.getName()));
    return new GreenSpace().id(domain.getId()).userId(domain.getUserId()).profile(profile);
  }

  public mg.vnnd.rtgiapi.rest.endpoint.repository.model.GreenSpace toDomain(GreenSpace rest) {
    GreenSpaceProfile profile = rest.getProfile();
    Location location = profile.getLocation();
    return mg.vnnd.rtgiapi.rest.endpoint.repository.model.GreenSpace.builder()
        .id(rest.getId())
        .userId(rest.getUserId())
        .address(location.getAddress())
        .longitude(location.getLongitude())
        .latitude(location.getLatitude())
        .name(location.getName())
        .ticketPrice(profile.getTicketPrice())
        .area(profile.getArea())
        .build();
  }
}
