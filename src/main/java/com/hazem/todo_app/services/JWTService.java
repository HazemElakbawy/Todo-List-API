package com.hazem.todo_app.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTService {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.access-token-expiration-time}")
  private long accessTokenExpiryTime;

  @Value("${jwt.refresh-token-expiration-time}")
  private long refreshTokenExpiryTime;

  public String getEmailFromToken(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  private String generateToken(String email, long expiryTime) {
    Instant now = Instant.now();
    return Jwts.builder()
        .subject(email)
        .issuedAt(Date.from(now))
        .expiration(Date.from(now.plus(expiryTime, ChronoUnit.MILLIS)))
        .signWith(getSigningKey())
        .compact();
  }
  
  public String generateAccessToken(String email) {
    return generateToken(email, accessTokenExpiryTime);
  }

  public String generateRefreshToken(String email) {
    return generateToken(email, refreshTokenExpiryTime);
  }

  public boolean validateToken(String token, String email) {
    final String emailFromToken = getEmailFromToken(token);
    return (emailFromToken.equals(email) && !isTokenExpired(token));
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(Date.from(Instant.now()));
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
        .verifyWith(getSigningKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
  }
}