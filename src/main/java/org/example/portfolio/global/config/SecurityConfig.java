package org.example.portfolio.global.config;

import lombok.RequiredArgsConstructor;
import org.example.portfolio.global.jwt.JwtAccessDeniedHandler;
import org.example.portfolio.global.jwt.JwtAuthenticationEntryPoint;
import org.example.portfolio.global.jwt.JwtAuthenticationFilter;
import org.example.portfolio.global.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableMethodSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

  private final String[] allowedUrls = {"/", "/swagger-ui/**", "/v3/**", "/sign-up", "/sign-in", "/oauth/**", "/login/**", "/actuator/prometheus"};
  private final TokenProvider tokenProvider;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //이 프로젝트는 쿠키와 세션을 사용하지 않기 때문에 세션 생성 정책을 STATELESS로 설정하고, CSRF도 비활성화 한다.
    return http
        .csrf(AbstractHttpConfigurer::disable)
        .headers((headerConfig) -> headerConfig.frameOptions(FrameOptionsConfig::sameOrigin))
        .authorizeHttpRequests(requests ->
            requests.requestMatchers(allowedUrls)
                .permitAll()  // requestMatchers의 인자로 전달된 url은 모두에게 허용
                .anyRequest().authenticated()  // 그 외의 모든 요청은 인증 필요
        )
        .sessionManagement(sessionManagement ->
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )  // 세션을 사용하지 않으므로 STATELESS 설정
        .addFilterBefore(new JwtAuthenticationFilter(tokenProvider),
            UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling((exceptionConfig) -> exceptionConfig.authenticationEntryPoint(
            jwtAuthenticationEntryPoint).accessDeniedHandler(jwtAccessDeniedHandler))
        .build();
  }
}
