package org.example.portfolio.user.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.user.adapter.in.dto.request.SignInRequest;
import org.example.portfolio.user.adapter.in.dto.request.SignUpRequest;
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
//  private final SignService signService;

  @Operation(summary = "회원 가입")
  @PostMapping("/sign-up")
  public ResponseEntity<Void> signUp(@RequestBody SignUpRequest request) {
//    return ApiResponse.success(signService.registMember(request));
  }

  @Operation(summary = "로그인")
  @PostMapping("/sign-in")
  public ResponseEntity<Void> signIn(@RequestBody SignInRequest request) {
//    return ApiResponse.success(signService.signIn(request));
  }
}
