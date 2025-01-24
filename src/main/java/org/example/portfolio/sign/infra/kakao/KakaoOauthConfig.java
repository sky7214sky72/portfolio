package org.example.portfolio.sign.infra.kakao;

import java.util.Arrays;
import java.util.Objects;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.kakao")
public record KakaoOauthConfig(
    String redirectUri,
    String clientId,
    String clientAuthenticationMethod,
    String authorizationGrantType,
    String[] scope
) {

  // equals() 메서드 오버라이드
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    KakaoOauthConfig that = (KakaoOauthConfig) o;
    return redirectUri.equals(that.redirectUri) &&
        clientId.equals(that.clientId) &&
        clientAuthenticationMethod.equals(that.clientAuthenticationMethod) &&
        authorizationGrantType.equals(that.authorizationGrantType) &&
        Arrays.equals(scope, that.scope);
  }

  // hashCode() 메서드 오버라이드
  @Override
  public int hashCode() {
    int result = Objects.hash(redirectUri, clientId, clientAuthenticationMethod, authorizationGrantType);
    result = 31 * result + Arrays.hashCode(scope);
    return result;
  }

  // toString() 메서드 오버라이드
  @Override
  public String toString() {
    return "KakaoOauthConfig{" +
        "redirectUri='" + redirectUri + '\'' +
        ", clientId='" + clientId + '\'' +
        ", clientAuthenticationMethod='" + clientAuthenticationMethod + '\'' +
        ", authorizationGrantType='" + authorizationGrantType + '\'' +
        ", scope=" + Arrays.toString(scope) +
        '}';
  }
}
