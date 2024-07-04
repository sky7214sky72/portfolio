package org.example.portfolio.baseball;

import io.restassured.RestAssured;
import org.example.portfolio.ApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class BaseballApiTest extends ApiTest {

  @Test
  void wrc조회() {
    RestAssured.given().log().all()
        .disableCsrf()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .get("/baseball/hitter")
        .then().log().all().extract();
  }
}
