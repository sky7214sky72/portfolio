package org.example.portfolio.sign;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.example.portfolio.ApiTest;
import org.example.portfolio.sign.domain.dto.request.SignInRequest;
import org.example.portfolio.sign.domain.dto.request.SignUpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class SignApiTest extends ApiTest {

  @Test
  void 회원가입() {
    SignUpRequest request = SignSteps.회원가입정보_생성();
    ExtractableResponse<Response> response = SignSteps.회원가입(request);
    assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
  }

  @Test
  void 로그인() {
    SignUpRequest signUpRequest = SignSteps.회원가입정보_생성();
    SignSteps.회원가입(signUpRequest);
    SignInRequest request = SignSteps.로그인정보_생성();
    ExtractableResponse<Response> response = SignSteps.로그인(request);
    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
  }
}
