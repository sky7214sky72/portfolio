package org.example.portfolio.word;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.example.portfolio.ApiTest;
import org.example.portfolio.word.adapter.in.dto.request.AddWordRequest;
import org.springframework.http.MediaType;

public class WordSteps extends ApiTest {

  public static ExtractableResponse<Response> 상품등록요청(
      AddWordRequest addWordRequest) {
    final ExtractableResponse<Response> response = RestAssured.given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(addWordRequest)
        .when()
        .post("/words")
        .then()
        .log().all().extract();
    return response;
  }

  public static AddWordRequest 상품등록요청_생성() {
    final String word = "word";
    final String mean = "단어";
    return new AddWordRequest(word, mean);
  }
}
