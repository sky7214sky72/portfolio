package org.example.portfolio.sign.infra.kakao;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.kakao")
public record KakaoOauthConfig(
    String redirectUri,
    String clientId,
    String clientAuthenticationMethod,
    String authorizationGrantType,
    String[] scope
) {

}
