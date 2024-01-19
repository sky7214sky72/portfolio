package org.example.portfolio.sign.application.port.in;

import org.example.portfolio.sign.adapter.in.dto.request.SignInRequest;
import org.example.portfolio.sign.adapter.in.dto.request.SignUpRequest;
import org.example.portfolio.sign.adapter.in.dto.response.SignInResponse;
import org.springframework.http.ResponseEntity;

public interface SignPort {

  ResponseEntity<Object> signUp(SignUpRequest request);

  ResponseEntity<SignInResponse> signIn(SignInRequest request);
}
