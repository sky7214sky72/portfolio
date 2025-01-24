package org.example.portfolio.global.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

  private static final Logger jwtLogger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
  private final TokenProvider tokenProvider;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    String token = parseBearerToken(httpServletRequest);
    String requestUrl = httpServletRequest.getRequestURI();

    if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
      User user = parseUserSpecification(token);
      AbstractAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(
          user, token, user.getAuthorities());
      authenticated.setDetails(new WebAuthenticationDetails(httpServletRequest));
      SecurityContextHolder.getContext().setAuthentication(authenticated);
      jwtLogger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", user.getUsername(),
          requestUrl);
    }
    filterChain.doFilter(request, response);
  }

  private String parseBearerToken(HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
        .filter(token -> token.startsWith("Bearer "))
        .map(token -> token.substring(7))
        .orElse(null);
  }

  private User parseUserSpecification(String token) {
    String[] split = Optional.ofNullable(token)
        .filter(subject -> subject.length() >= 10)
        .map(tokenProvider::validateTokenAndGetSubject)
        .orElse("anonymous:anonymous")
        .split(":");
    return new User(split[0], "", List.of(new SimpleGrantedAuthority(split[1])));
  }
}
