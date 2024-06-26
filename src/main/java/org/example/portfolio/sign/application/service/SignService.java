package org.example.portfolio.sign.application.service;

import static org.example.portfolio.global.domain.ErrorCode.ALREADY_USER_SAVE;
import static org.example.portfolio.global.domain.ErrorCode.MAIL_PASSWORD_INVALID;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.example.portfolio.global.domain.ErrorCode;
import org.example.portfolio.global.exception.CustomException;
import org.example.portfolio.global.jwt.TokenProvider;
import org.example.portfolio.sign.adapter.in.dto.request.SignInRequest;
import org.example.portfolio.sign.adapter.in.dto.request.SignUpRequest;
import org.example.portfolio.sign.adapter.in.dto.response.SignInResponse;
import org.example.portfolio.sign.application.port.in.SignPort;
import org.example.portfolio.sign.application.port.out.SignRepository;
import org.example.portfolio.sign.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignService implements SignPort {

  private final SignRepository signRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenProvider tokenProvider;

  @Override
  @Transactional
  public ResponseEntity<Object> signUp(SignUpRequest request) {
    if (signRepository.existsByMail(request.mail())) {
      throw new CustomException(ALREADY_USER_SAVE);
    }
    User user = signRepository.save(User.from(request, passwordEncoder));
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  @Override
  @Transactional
  public ResponseEntity<SignInResponse> signIn(SignInRequest request) {
    User user = signRepository.findByMail(request.mail())
        .filter(f -> passwordEncoder.matches(request.password(), f.getPassword()))
        .orElseThrow(() -> new CustomException(MAIL_PASSWORD_INVALID));
    if (!StringUtils.isEmpty(user.getProvider())) {
      String provider = user.getProvider().toUpperCase();
      switch (provider) {
        case "KAKAO":
        case "GOOGLE":
        case "NAVER":
          throw new CustomException(ErrorCode.valueOf(provider + "_USER"));
        default:
          throw new CustomException(ErrorCode.INVALID_PARAMETER);
      }
    }
    String token = tokenProvider.createToken(String.format("%s:%s", user.getId(), user.getType()));
    return ResponseEntity.ok(new SignInResponse(user.getName(), user.getMail(), token));
  }
}
