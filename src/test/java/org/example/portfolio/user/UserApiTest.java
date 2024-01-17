package org.example.portfolio.user;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.example.portfolio.ApiTest;
import org.example.portfolio.user.adapter.in.dto.request.SignUpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class UserApiTest extends ApiTest {

  @Test
  void 회원가입() {
    SignUpRequest request = UserSteps.회원가입정보_생성();
    final ExtractableResponse<Response> response = RestAssured.given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when()
        .post("/sign-up")
        .then().log().all().extract();
    assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
  }
}
