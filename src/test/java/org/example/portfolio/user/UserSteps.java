package org.example.portfolio.user;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.example.portfolio.user.adapter.in.dto.request.SignInRequest;
import org.example.portfolio.user.adapter.in.dto.request.SignUpRequest;
import org.springframework.http.MediaType;

public class UserSteps {

  public static SignUpRequest 회원가입정보_생성() {
    return new SignUpRequest("1234", "최원석", "cws070993@gmail.com");
  }

  public static SignInRequest 로그인정보_생성() {
    return new SignInRequest("cws070993@gmail.com", "1234");
  }

  public static ExtractableResponse<Response> 회원가입(
      SignUpRequest request) {
    return RestAssured.given().log().all()
        .disableCsrf()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when()
        .post("/sign-up")
        .then().log().all().extract();
  }

  public static ExtractableResponse<Response> 로그인(
      SignInRequest request) {
    return RestAssured.given().log().all()
        .disableCsrf()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when()
        .post("/sign-in")
        .then().log().all().extract();
  }
}
