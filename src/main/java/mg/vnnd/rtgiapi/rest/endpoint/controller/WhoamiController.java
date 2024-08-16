package mg.vnnd.rtgiapi.rest.endpoint.controller;

import lombok.AllArgsConstructor;
import mg.vnnd.rtgiapi.endpoint.rest.model.Whoami;
import mg.vnnd.rtgiapi.rest.endpoint.controller.mapper.UserMapper;
import mg.vnnd.rtgiapi.rest.endpoint.security.model.Principal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WhoamiController {
  private final UserMapper userMapper;

  @GetMapping("/whoami")
  public Whoami whoami(@AuthenticationPrincipal Principal principal) {
    return new Whoami().bearer(principal.getBearer()).user(userMapper.toRest(principal.getUser()));
  }
}
