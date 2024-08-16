package mg.vnnd.rtgiapi.rest.endpoint.controller;

import lombok.AllArgsConstructor;
import mg.vnnd.rtgiapi.endpoint.rest.model.BearerToken;
import mg.vnnd.rtgiapi.endpoint.rest.model.Credentials;
import mg.vnnd.rtgiapi.rest.endpoint.service.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TokenController {
  private final TokenService service;

  @PostMapping("/token")
  public BearerToken createToken(@RequestBody Credentials credentials) {
    return service.getBearerToken(credentials);
  }
}
