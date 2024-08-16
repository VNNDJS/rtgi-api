package mg.vnnd.rtgiapi.rest.endpoint.security;

import lombok.AllArgsConstructor;
import mg.vnnd.rtgiapi.rest.endpoint.security.model.Principal;
import mg.vnnd.rtgiapi.rest.endpoint.service.UserService;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AuthProvider extends AbstractUserDetailsAuthenticationProvider {
  private final UserService userService;
  private final JwtService jwtService;

  @Override
  protected void additionalAuthenticationChecks(
      UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {
    // DO NOTHING
    return;
  }

  @Override
  protected UserDetails retrieveUser(
      String username, UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {
    String bearer = getBearerFromHeader(authentication);
    if (bearer == null) {
      throw new UsernameNotFoundException("Bad credentials"); // NOSONAR
    }
    if (jwtService.isTokenExpired(bearer)) {
      throw new AuthenticationServiceException("token expired");
    }
    var extractedEmail = jwtService.extractUsername(bearer);
    var user = userService.findByEmail(extractedEmail);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("Bad credentials");
    }
    return new Principal(bearer, user.get());
  }

  public static final String BEARER_PREFIX = "Bearer ";

  public static String getBearerFromHeader(
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
    Object tokenObject = usernamePasswordAuthenticationToken.getCredentials();
    if (!(tokenObject instanceof String token) || !token.startsWith(BEARER_PREFIX)) {
      return null;
    }
    return token.substring(BEARER_PREFIX.length()).trim();
  }
}
