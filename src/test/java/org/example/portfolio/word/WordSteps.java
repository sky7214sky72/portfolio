package org.example.portfolio.word;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import org.example.portfolio.ApiTest;
import org.example.portfolio.word.adapter.in.dto.request.AddWordRequest;
import org.springframework.http.MediaType;

public class WordSteps extends ApiTest {

  public static ExtractableResponse<Response> 단어등록요청(
      List<AddWordRequest> addWordRequestList) {
    final ExtractableResponse<Response> response = RestAssured.given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(addWordRequestList)
        .when()
        .post("/word")
        .then()
        .log().all().extract();
    return response;
  }

  public static List<AddWordRequest> 단어등록요청_생성() {
    final List<String> words = new ArrayList<>();
    words.add("word1");
    words.add("word2");
    words.add("word3");
    final List<String> means = new ArrayList<>();
    means.add("단어1");
    means.add("단어2");
    means.add("단어3");
    List<AddWordRequest> result = new ArrayList<>();
    for (int i = 0; i < words.size(); i++) {
      result.add(new AddWordRequest(words.get(i), means.get(i)));
    }
    return result;
  }
}
