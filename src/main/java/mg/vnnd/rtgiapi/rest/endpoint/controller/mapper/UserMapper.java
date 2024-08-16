package mg.vnnd.rtgiapi.rest.endpoint.controller.mapper;

import mg.vnnd.rtgiapi.endpoint.rest.model.User;
import mg.vnnd.rtgiapi.endpoint.rest.model.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  public User toRest(mg.vnnd.rtgiapi.rest.endpoint.repository.model.User user) {
    var profile =
        new UserProfile()
            .email(user.getEmail())
            .birthdate(user.getBirthdate())
            .type(user.getType())
            .name(user.getName());
    return new User()
      .profile(profile)
      .id(user.getId());
  }
}
