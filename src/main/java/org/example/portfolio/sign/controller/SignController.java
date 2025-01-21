package org.example.portfolio.sign.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.portfolio.sign.domain.OauthServerType;
import org.example.portfolio.sign.domain.dto.request.SignInRequest;
import org.example.portfolio.sign.domain.dto.request.SignUpRequest;
import org.example.portfolio.sign.domain.dto.response.SignInResponse;
import org.example.portfolio.sign.service.OauthService;
import org.example.portfolio.sign.service.SignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 가입 및 로그인")
@RequiredArgsConstructor
@RestController
@RequestMapping("/word")
public class SignController {

  private final SignService signService;
  private final OauthService oauthService;

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

  @SneakyThrows
  @GetMapping("/oauth/{oauthServerType}")
  ResponseEntity<Void> redirectAuthCodeRequestUrl(
      @PathVariable OauthServerType oauthServerType,
      HttpServletResponse response
  ) {
    String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);
    response.sendRedirect(redirectUrl);
    return ResponseEntity.ok(null);
  }

  @GetMapping("/login/{oauthServerType}")
  ResponseEntity<SignInResponse> login(
      @PathVariable OauthServerType oauthServerType,
      @RequestParam("code") String code
  ) {
    return oauthService.login(oauthServerType, code);
  }
}
