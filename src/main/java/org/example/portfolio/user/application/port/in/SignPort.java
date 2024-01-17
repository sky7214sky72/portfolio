package org.example.portfolio.user.application.port.in;

import org.example.portfolio.user.adapter.in.dto.request.SignInRequest;
import org.example.portfolio.user.adapter.in.dto.request.SignUpRequest;

public interface SignPort {

  void signUp(SignUpRequest request);

  void signIn(SignInRequest request);
}
