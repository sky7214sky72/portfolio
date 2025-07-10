package org.example.portfolio.sign.infra.google.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import org.example.portfolio.global.domain.MemberType;
import org.example.portfolio.sign.domain.OauthServerType;
import org.example.portfolio.sign.domain.User;

@JsonNaming(SnakeCaseStrategy.class)
public record GoogleMemberResponse(
    String sub,
    String name,
    String email,
    String picture
) {
  public User toDomain() {
    return User.builder()
        .type(MemberType.USER.name())
        .name(name)
        .mail(email)
        .provider(OauthServerType.GOOGLE.name())
        .build();
  }
}