package org.example.portfolio.global.config;

import lombok.RequiredArgsConstructor;
import org.example.portfolio.sign.infra.OauthServerTypeConverter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@ComponentScan
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("http://localhost:8080", "https://kauth.kakao.com/oauth/authorize",
            "https://kauth.kakao.com/oauth/authorize", "https://kapi.kakao.com/v2/user/me")
        .allowedMethods(
            HttpMethod.GET.name(),
            HttpMethod.POST.name(),
            HttpMethod.PUT.name(),
            HttpMethod.DELETE.name(),
            HttpMethod.PATCH.name()
        )
        .allowCredentials(true)
        .exposedHeaders("Authorization", "Content-Type");
  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new OauthServerTypeConverter());
  }
}
