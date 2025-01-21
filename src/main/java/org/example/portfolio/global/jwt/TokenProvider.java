package org.example.portfolio.global.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import javax.crypto.SecretKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:application-jwt.yml")
@Component
public class TokenProvider implements InitializingBean {

  private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
  private final String secretKey;
  private final long expirationHours;
  private final String issuer;
  private SecretKey key;

  public TokenProvider(
      @Value("${secret-key}") String secretKey,
      @Value("${expiration-hours}") long expirationHours,
      @Value("${issuer}") String issuer) {
    this.secretKey = secretKey;
    this.expirationHours = expirationHours;
    this.issuer = issuer;
  }

  //의존성 주입 후 주입 받은 시크릿 값을 디코드해서 키에 저장
  @Override
  public void afterPropertiesSet() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    this.key = Keys.hmacShaKeyFor(keyBytes);
  }

  public String validateTokenAndGetSubject(String token) {
    return Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
  }

  public long getTokenSubject(String token) {
    String[] split = Optional.ofNullable(token)
        .filter(subject -> subject.length() >= 10)
        .map(data -> Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject())
        .orElse("anonymous:anonymous")
        .split(":");
    return Long.parseLong(split[0]);
  }

  public String createToken(String userSpecification) {
    return Jwts.builder()
        .signWith(key)   // HS512 알고리즘을 사용하여 secretKey를 이용해 서명
        .subject(userSpecification)  // JWT 토큰 제목
        .issuer(issuer)  // JWT 토큰 발급자
        .issuedAt(Timestamp.valueOf(LocalDateTime.now()))    // JWT 토큰 발급 시간
        .expiration(
            Date.from(Instant.now().plus(expirationHours, ChronoUnit.HOURS)))    // JWT 토큰 만료 시간
        .compact(); // JWT 토큰 생성
  }

  /**
   * . 토큰 유효성 검사
   */
  public boolean validateToken(String token) {
    try {
      Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
      return true;
    } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
      logger.info("잘못된 JWT 서명입니다.");
      return false;
    } catch (ExpiredJwtException e) {
      logger.info("만료된 JWT 토큰입니다.");
      return false;
    } catch (UnsupportedJwtException e) {
      logger.info("지원되지 않는 JWT 토큰입니다.");
      return false;
    } catch (IllegalArgumentException e) {
      logger.info("JWT 토큰이 잘못되었습니다.");
      return false;
    }
  }
}
