package org.example.portfolio.sign.service;

import lombok.RequiredArgsConstructor;
import org.example.portfolio.global.jwt.TokenProvider;
import org.example.portfolio.sign.domain.dto.response.SignInResponse;
import org.example.portfolio.sign.repository.SignRepository;
import org.example.portfolio.sign.domain.OauthServerType;
import org.example.portfolio.sign.domain.User;
import org.example.portfolio.sign.domain.authcode.AuthCodeRequestUrlProviderComposite;
import org.example.portfolio.sign.domain.client.OauthMemberClientComposite;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthService {

  private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
  private final OauthMemberClientComposite oauthMemberClientComposite;
  private final SignRepository signRepository;
  private final TokenProvider tokenProvider;
  private final PasswordEncoder passwordEncoder;

  public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
    return authCodeRequestUrlProviderComposite.provide(oauthServerType);
  }

  public ResponseEntity<SignInResponse> login(OauthServerType oauthServerType, String authCode) {
    User oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);
    oauthMember.updatePassword(passwordEncoder, oauthMember.getMail());
    User saved = signRepository.findByMail(oauthMember.getMail())
        .orElseGet(() -> signRepository.save(oauthMember));
    String token = tokenProvider.createToken(String.format("%s:%s", saved.getId(), saved.getType()));
    return ResponseEntity.ok(new SignInResponse(saved.getName(), saved.getMail(), token));
  }
}
