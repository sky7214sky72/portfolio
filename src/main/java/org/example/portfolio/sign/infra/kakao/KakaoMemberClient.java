package org.example.portfolio.sign.infra.kakao;

import lombok.RequiredArgsConstructor;
import org.example.portfolio.sign.domain.OauthServerType;
import org.example.portfolio.sign.domain.User;
import org.example.portfolio.sign.domain.client.OauthMemberClient;
import org.example.portfolio.sign.infra.kakao.client.KakaoApiClient;
import org.example.portfolio.sign.infra.kakao.dto.KakaoMemberResponse;
import org.example.portfolio.sign.infra.kakao.dto.KakaoToken;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class KakaoMemberClient implements OauthMemberClient {

  private final KakaoApiClient kakaoApiClient;
  private final KakaoOauthConfig kakaoOauthConfig;

  @Override
  public OauthServerType supportServer() {
    return OauthServerType.KAKAO;
  }

  @Override
  public User fetch(String authCode) {
    KakaoToken tokenInfo = kakaoApiClient.fetchToken(tokenRequestParams(authCode)); // (1)
    KakaoMemberResponse kakaoMemberResponse =
        kakaoApiClient.fetchMember("Bearer " + tokenInfo.accessToken());  // (2)
    return kakaoMemberResponse.toDomain();
  }

  private MultiValueMap<String, String> tokenRequestParams(String authCode) {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", kakaoOauthConfig.clientId());
    params.add("redirect_uri", kakaoOauthConfig.redirectUri());
    params.add("code", authCode);
    return params;
  }
}
