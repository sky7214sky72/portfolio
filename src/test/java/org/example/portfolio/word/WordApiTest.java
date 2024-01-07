package org.example.portfolio.word;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.example.portfolio.ApiTest;
import org.example.portfolio.word.adapter.in.dto.request.AddWordRequest;
import org.example.portfolio.word.adapter.in.dto.response.GetWordResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class WordApiTest extends ApiTest {

  @Test
  void 단어등록() {
    List<AddWordRequest> addWordRequestList = WordSteps.단어등록요청_생성();
    // API 요청
    final ExtractableResponse<Response> response = WordSteps.단어등록요청(addWordRequestList);
    assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
  }

  @Test
  void 단어상세조회() {
    WordSteps.단어등록요청(WordSteps.단어등록요청_생성());
    Long wordId = 1L;
    final ExtractableResponse<Response> response = RestAssured.given().log().all()
        .when()
        .get("/words/{wordId}", wordId)
        .then().log().all()
        .extract();
    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
  }

  @Test
  void 단어리스트조회() {
    //단어 목록 조회
    final ExtractableResponse<Response> response = RestAssured.given().log().all()
        .when()
        .queryParam("keyword", "단어3")
        .get("/words")
        .then().log().all()
        .extract();
    List<GetWordResponse> contentList = response.jsonPath().getList("content", GetWordResponse.class);
    contentList.forEach(list -> {
      assertThat(list.mean()).isEqualTo("단어3");
    });
    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
  }
}
