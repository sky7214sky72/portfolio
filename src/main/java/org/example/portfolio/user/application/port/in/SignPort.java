package org.example.portfolio.user.application.port.in;

import org.example.portfolio.user.adapter.in.dto.request.SignInRequest;
import org.example.portfolio.user.adapter.in.dto.request.SignUpRequest;
import org.example.portfolio.user.adapter.in.dto.response.SignInResponse;
import org.springframework.http.ResponseEntity;

public interface SignPort {

  ResponseEntity<Object> signUp(SignUpRequest request);

  SignInResponse signIn(SignInRequest request);
}
