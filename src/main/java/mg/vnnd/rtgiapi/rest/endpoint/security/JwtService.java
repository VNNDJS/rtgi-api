package mg.vnnd.rtgiapi.rest.endpoint.security;

import static io.jsonwebtoken.SignatureAlgorithm.*;
import static io.jsonwebtoken.io.Decoders.BASE64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtService {
  private final String secret;

  public JwtService(@Value("${app.jwt.secret}") String secret) {
    this.secret = secret;
  }

  // Generate token with given user name
  public String generateToken(String email) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, email);
  }

  // Create a JWT token with specified claims and subject (email)
  private String createToken(Map<String, Object> claims, String email) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(email)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(
            new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // Token valid for 30 minutes
        .signWith(getSignKey(), HS256)
        .compact();
  }

  // Get the signing key for JWT token
  private Key getSignKey() {
    byte[] keyBytes = BASE64.decode(secret);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  // Extract the username from the token
  public String extractUsername(String token) throws JwtException {
    return extractClaim(token, Claims::getSubject);
  }

  // Extract the expiration date from the token
  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  // Extract a claim from the token
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  // Extract all claims from the token
  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(getSignKey()).build().parseSignedClaims(token).getPayload();
  }

  // Check if the token is expired
  public boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }
}
