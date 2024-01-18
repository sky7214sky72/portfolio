package org.example.portfolio.user.application.service;

import lombok.RequiredArgsConstructor;
import org.example.portfolio.global.jwt.TokenProvider;
import org.example.portfolio.user.adapter.in.dto.request.SignInRequest;
import org.example.portfolio.user.adapter.in.dto.request.SignUpRequest;
import org.example.portfolio.user.adapter.in.dto.response.SignInResponse;
import org.example.portfolio.user.application.port.in.SignPort;
import org.example.portfolio.user.application.port.out.SignRepository;
import org.example.portfolio.user.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SignService implements SignPort {

  private final SignRepository signRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenProvider tokenProvider;

  @Override
  public void signUp(SignUpRequest request) {
    signRepository.save(User.from(request, passwordEncoder));
  }

  @Override
  public SignInResponse signIn(SignInRequest request) {
    User user = signRepository.findByMail(request.mail())
        .filter(f -> passwordEncoder.matches(request.password(), f.getPassword()))
        .orElseThrow(() -> new IllegalArgumentException("아이디 비밀번호가 일치하지 않습니다."));
    String token = tokenProvider.createToken(String.format("%s:%s", user.getId(), user.getType()));
    return new SignInResponse(user.getName(), user.getMail(), token);
  }
}
