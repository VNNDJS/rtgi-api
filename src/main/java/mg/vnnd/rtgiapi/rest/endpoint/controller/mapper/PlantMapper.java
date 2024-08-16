package mg.vnnd.rtgiapi.rest.endpoint.controller.mapper;

import mg.vnnd.rtgiapi.endpoint.rest.model.Plant;
import org.springframework.stereotype.Component;

@Component
public class PlantMapper {
  public Plant toRest(mg.vnnd.rtgiapi.rest.endpoint.repository.model.Plant plant) {
    return new Plant()
        .id(plant.getId())
        .isEndemic(plant.isEndemic())
        .name(plant.getName())
        .number(plant.getNumber())
        .greenSpaceId(plant.getGreenSpaceId());
  }

  public mg.vnnd.rtgiapi.rest.endpoint.repository.model.Plant toDomain(Plant rest) {
    return mg.vnnd.rtgiapi.rest.endpoint.repository.model.Plant.builder()
        .id(rest.getId())
        .greenSpaceId(rest.getGreenSpaceId())
        .name(rest.getName())
        .number(rest.getNumber())
        .greenSpaceId(rest.getGreenSpaceId())
        .build();
  }
}
