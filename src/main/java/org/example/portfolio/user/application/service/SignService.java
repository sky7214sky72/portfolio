package org.example.portfolio.user.application.service;

import lombok.RequiredArgsConstructor;
import org.example.portfolio.user.adapter.in.dto.request.SignInRequest;
import org.example.portfolio.user.adapter.in.dto.request.SignUpRequest;
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

  @Override
  public void signUp(SignUpRequest request) {
    signRepository.save(User.from(request, passwordEncoder));
  }

  @Override
  public void signIn(SignInRequest request) {

  }
}
