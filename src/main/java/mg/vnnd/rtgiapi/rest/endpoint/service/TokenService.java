package mg.vnnd.rtgiapi.rest.endpoint.service;

import lombok.AllArgsConstructor;
import mg.vnnd.rtgiapi.endpoint.rest.model.BearerToken;
import mg.vnnd.rtgiapi.endpoint.rest.model.Credentials;
import mg.vnnd.rtgiapi.rest.endpoint.security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TokenService {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public BearerToken getBearerToken(Credentials credentials) {
    String email = credentials.getEmail();
    if (emailPasswordMatches(email, credentials.getPassword())) {
      return new BearerToken().token(jwtService.generateToken(email));
    }
    throw new BadCredentialsException("Invalid credentials");
  }

  private boolean emailPasswordMatches(String username, String password) {
    var encodedPassword = userService.getByEmail(username).getEncodedPassword();
    return passwordEncoder.matches(password, encodedPassword);
  }
}
