package org.example.portfolio.word;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class UserSteps {

  public static ExtractableResponse<Response> 외운단어_체크(String token) {
    return RestAssured.given().log().all()
        .headers(
            "Authorization",
            "Bearer " + token,
            "Content-Type",
            ContentType.JSON,
            "Accept",
            ContentType.JSON)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .post("/word/memorized/1")
        .then()
        .log().all().extract();
  }

  public static ExtractableResponse<Response> 내가_외운_단어_조회(String token) {
    return RestAssured.given().log().all()
        .headers(
            "Authorization",
            "Bearer " + token,
            "Content-Type",
            ContentType.JSON,
            "Accept",
            ContentType.JSON)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .get("/word/memorized")
        .then()
        .log().all().extract();
  }
}
