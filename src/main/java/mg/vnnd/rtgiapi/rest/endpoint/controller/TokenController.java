package mg.vnnd.rtgiapi.rest.endpoint.controller;

import mg.vnnd.rtgiapi.endpoint.rest.model.BearerToken;
import mg.vnnd.rtgiapi.endpoint.rest.model.Credentials;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
  @PostMapping("/token")
  public BearerToken createToken(@RequestBody Credentials credentials) {
    return new BearerToken().token("token");
  }
}
