package org.example.portfolio.sign.service;

import org.example.portfolio.sign.domain.dto.request.SignInRequest;
import org.example.portfolio.sign.domain.dto.request.SignUpRequest;
import org.example.portfolio.sign.domain.dto.response.SignInResponse;
import org.springframework.http.ResponseEntity;

public interface SignService {

  ResponseEntity<Object> signUp(SignUpRequest request);

  ResponseEntity<SignInResponse> signIn(SignInRequest request);
}
