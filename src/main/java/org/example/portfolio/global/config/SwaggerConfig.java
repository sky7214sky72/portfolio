package org.example.portfolio.global.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  private static final String SECURITY_SCHEME_NAME = "authorization";

  @Bean
  public GroupedOpenApi getSignApi() {
    return GroupedOpenApi.builder()
        .group("sign")
        .pathsToMatch("/login/**")
        .build();
  }

  @Bean
  public GroupedOpenApi getWordApi() {
    return GroupedOpenApi.builder()
        .group("word")
        .pathsToMatch("/word/**")
        .build();
  }

  @Bean
  public GroupedOpenApi getBaseballApi() {
    return GroupedOpenApi.builder()
        .group("baseball")
        .pathsToMatch("/baseball/**")
        .build();
  }

  @Bean
  public GroupedOpenApi getLottoApi() {
    return GroupedOpenApi.builder()
        .group("lotto")
        .pathsToMatch("/lotto/**")
        .build();
  }

  @Bean
  public OpenAPI swaggerApi() {
    return new OpenAPI()
        .components(new Components()
            .addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                .name(SECURITY_SCHEME_NAME)
                .type(Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
            ))
        .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
        .info(getInfo());
  }

  private Info getInfo() {
    return new Info()
        .title("포트폴리오 API 문서")
        .description("포트폴리오 API 문서입니다.")
        .version("1.0.0");
  }
}
