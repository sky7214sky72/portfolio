package org.example.portfolio.user.application.port.in;

import org.example.portfolio.user.adapter.in.dto.request.SignInRequest;
import org.example.portfolio.user.adapter.in.dto.request.SignUpRequest;
import org.example.portfolio.user.adapter.in.dto.response.SignInResponse;

public interface SignPort {

  void signUp(SignUpRequest request);

  SignInResponse signIn(SignInRequest request);
}
