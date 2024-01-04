package org.example.portfolio.word;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.example.portfolio.ApiTest;
import org.example.portfolio.word.adapter.in.dto.AddWordRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class WordApiTest extends ApiTest {

  @Test
  void 단어등록() {
    AddWordRequest addWordRequest = 상품등록요청_생성();
    // API 요청
    final ExtractableResponse<Response> response = RestAssured.given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(addWordRequest)
        .when()
        .post("/words")
        .then()
        .log().all().extract();
    assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
  }

  private static AddWordRequest 상품등록요청_생성() {
    final String word = "word";
    final String mean = "단어";
    return new AddWordRequest(word, mean);
  }

}
