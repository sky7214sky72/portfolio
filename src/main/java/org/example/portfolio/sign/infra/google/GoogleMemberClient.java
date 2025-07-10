package org.example.portfolio.sign.infra.google;

import lombok.RequiredArgsConstructor;
import org.example.portfolio.sign.domain.OauthServerType;
import org.example.portfolio.sign.domain.User;
import org.example.portfolio.sign.domain.client.OauthMemberClient;
import org.example.portfolio.sign.infra.google.client.GoogleApiClient;
import org.example.portfolio.sign.infra.google.dto.GoogleMemberResponse;
import org.example.portfolio.sign.infra.google.dto.GoogleToken;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class GoogleMemberClient implements OauthMemberClient {

  private final GoogleApiClient googleApiClient;
  private final GoogleOauthConfig googleOauthConfig;

  @Override
  public OauthServerType supportServer() {
    return OauthServerType.GOOGLE;
  }

  @Override
  public User fetch(String authCode) {
    GoogleToken token = googleApiClient.fetchToken(tokenRequestParams(authCode));
    GoogleMemberResponse member = googleApiClient.fetchMember("Bearer " + token.accessToken());
    return member.toDomain();
  }

  private MultiValueMap<String, String> tokenRequestParams(String authCode) {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", googleOauthConfig.clientId());
    params.add("client_secret", googleOauthConfig.clientSecret());
    params.add("redirect_uri", googleOauthConfig.redirectUri());
    params.add("code", authCode);
    return params;
  }
}
