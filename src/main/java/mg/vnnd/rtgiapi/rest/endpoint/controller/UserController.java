package mg.vnnd.rtgiapi.rest.endpoint.controller;

import lombok.AllArgsConstructor;
import mg.vnnd.rtgiapi.endpoint.rest.model.SignUpUser;
import mg.vnnd.rtgiapi.endpoint.rest.model.User;
import mg.vnnd.rtgiapi.rest.endpoint.controller.mapper.UserMapper;
import mg.vnnd.rtgiapi.rest.endpoint.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
  private final UserService service;
  private final UserMapper mapper;

  @PutMapping("/signup")
  public User signup(@RequestBody SignUpUser user) {
    return mapper.toRest(service.createUser(user));
  }
}
