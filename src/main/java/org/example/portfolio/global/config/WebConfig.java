package org.example.portfolio.global.config;

import lombok.RequiredArgsConstructor;
import org.example.portfolio.sign.infra.OauthServerTypeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // 기본 핸들러를 삭제하거나, 빈 디렉토리로 설정
    registry.addResourceHandler("/**")
        .addResourceLocations("classpath:/no-static-resources/");
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedOrigins("http://localhost:8080").allowedMethods(
            HttpMethod.GET.name(),
            HttpMethod.POST.name(),
            HttpMethod.PUT.name(),
            HttpMethod.DELETE.name(),
            HttpMethod.PATCH.name()
        )
        .allowCredentials(true)
        .exposedHeaders("*");
  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new OauthServerTypeConverter());
  }
}
