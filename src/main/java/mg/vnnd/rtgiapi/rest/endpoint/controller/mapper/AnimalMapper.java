package mg.vnnd.rtgiapi.rest.endpoint.controller.mapper;

import mg.vnnd.rtgiapi.endpoint.rest.model.Animal;
import org.springframework.stereotype.Component;

@Component
public class AnimalMapper {
  public Animal toRest(mg.vnnd.rtgiapi.rest.endpoint.repository.model.Animal animal) {
    return new Animal()
        .id(animal.getId())
        .gender(animal.getGender())
        .isEndemic(animal.isEndemic())
        .name(animal.getName())
        .number(animal.getNumber())
        .greenSpaceId(animal.getGreenSpaceId());
  }

  public mg.vnnd.rtgiapi.rest.endpoint.repository.model.Animal toDomain(Animal rest) {
    return mg.vnnd.rtgiapi.rest.endpoint.repository.model.Animal.builder()
        .id(rest.getId())
        .gender(rest.getGender())
        .greenSpaceId(rest.getGreenSpaceId())
        .name(rest.getName())
        .number(rest.getNumber())
        .greenSpaceId(rest.getGreenSpaceId())
        .build();
  }
}
