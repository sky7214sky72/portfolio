package org.example.portfolio.sign.infra.google.authcode;

import lombok.RequiredArgsConstructor;
import org.example.portfolio.sign.domain.OauthServerType;
import org.example.portfolio.sign.domain.authcode.AuthCodeRequestUrlProvider;
import org.example.portfolio.sign.infra.google.GoogleOauthConfig;
import org.example.portfolio.sign.infra.kakao.KakaoOauthConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class GoogleAuthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider {

  private final GoogleOauthConfig googleOauthConfig;

  @Override
  public OauthServerType supportServer() {
    return OauthServerType.GOOGLE;
  }

  @Override
  public String provide() {
    return UriComponentsBuilder
        .fromUriString("https://accounts.google.com/o/oauth2/v2/auth")
        .queryParam("response_type", "code")
        .queryParam("client_id", googleOauthConfig.clientId())
        .queryParam("redirect_uri", googleOauthConfig.redirectUri())
        .queryParam("scope", String.join(" ", googleOauthConfig.scope()))
        .queryParam("access_type", "offline")
        .toUriString();
  }
}
