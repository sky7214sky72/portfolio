package org.example.portfolio.word;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.example.portfolio.ApiTest;
import org.example.portfolio.word.adapter.in.dto.request.AddWordRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class WordApiTest extends ApiTest {

  @Test
  void 단어등록() {
    AddWordRequest addWordRequest = WordSteps.상품등록요청_생성();
    // API 요청
    final ExtractableResponse<Response> response = WordSteps.상품등록요청(addWordRequest);
    assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
  }

}
