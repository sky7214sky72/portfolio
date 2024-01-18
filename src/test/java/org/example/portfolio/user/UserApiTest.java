package org.example.portfolio.user;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.example.portfolio.ApiTest;
import org.example.portfolio.user.adapter.in.dto.request.SignInRequest;
import org.example.portfolio.user.adapter.in.dto.request.SignUpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class UserApiTest extends ApiTest {

  @Test
  void 회원가입() {
    SignUpRequest request = UserSteps.회원가입정보_생성();
    ExtractableResponse<Response> response = UserSteps.회원가입(request);
    assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
  }

  @Test
  void 로그인() {
    SignUpRequest signUpRequest = UserSteps.회원가입정보_생성();
    UserSteps.회원가입(signUpRequest);
    SignInRequest request = UserSteps.로그인정보_생성();
    ExtractableResponse<Response> response = UserSteps.로그인(request);
    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
  }
}
