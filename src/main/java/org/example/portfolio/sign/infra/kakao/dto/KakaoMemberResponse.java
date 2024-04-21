package org.example.portfolio.sign.infra.kakao.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import org.example.portfolio.global.domain.MemberType;
import org.example.portfolio.sign.domain.OauthServerType;
import org.example.portfolio.sign.domain.User;

@JsonNaming(SnakeCaseStrategy.class)
public record KakaoMemberResponse(
    Long id,
    boolean hasSignedUp,
    LocalDateTime connectedAt,
    KakaoAccount kakaoAccount
) {

  public User toDomain() {
    return User.builder()
        .type(MemberType.USER.name())
        .name(kakaoAccount.profile.nickname)
        .mail(kakaoAccount.email)
        .build();
  }

  @JsonNaming(SnakeCaseStrategy.class)
  public record KakaoAccount(
      boolean profileNeedsAgreement,
      boolean profileNicknameNeedsAgreement,
      boolean profileImageNeedsAgreement,
      Profile profile,
      boolean nameNeedsAgreement,
      String name,
      boolean emailNeedsAgreement,
      boolean isEmailValid,
      boolean isEmailVerified,
      String email,
      boolean ageRangeNeedsAgreement,
      String ageRange,
      boolean birthyearNeedsAgreement,
      String birthyear,
      boolean birthdayNeedsAgreement,
      String birthday,
      String birthdayType,
      boolean genderNeedsAgreement,
      String gender,
      boolean phoneNumberNeedsAgreement,
      String phoneNumber,
      boolean ciNeedsAgreement,
      String ci,
      LocalDateTime ciAuthenticatedAt
  ) {

  }

  @JsonNaming(SnakeCaseStrategy.class)
  public record Profile(
      String nickname,
      String thumbnailImageUrl,
      String profileImageUrl,
      boolean isDefaultImage
  ) {

  }
}