package org.example.portfolio.user.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.user.adapter.in.dto.request.SignInRequest;
import org.example.portfolio.user.adapter.in.dto.request.SignUpRequest;
import org.example.portfolio.user.adapter.in.dto.response.SignInResponse;
import org.example.portfolio.user.application.service.SignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 가입 및 로그인")
@RequiredArgsConstructor
@RestController
@RequestMapping
public class SignController {

  private final SignService signService;

  @Operation(summary = "회원 가입")
  @PostMapping("/sign-up")
  public ResponseEntity<Object> signUp(@RequestBody SignUpRequest request) throws Exception {
    return signService.signUp(request);
  }

  @Operation(summary = "로그인")
  @PostMapping("/sign-in")
  public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest request) throws Exception {
    return signService.signIn(request);
  }
}
