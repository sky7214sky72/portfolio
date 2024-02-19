package org.example.portfolio.user;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.example.portfolio.ApiTest;
import org.example.portfolio.global.domain.MemberType;
import org.example.portfolio.global.jwt.TokenProvider;
import org.example.portfolio.sign.SignSteps;
import org.example.portfolio.sign.adapter.in.dto.request.SignUpRequest;
import org.example.portfolio.sign.application.port.out.SignRepository;
import org.example.portfolio.sign.domain.User;
import org.example.portfolio.user.adapter.in.dto.request.MemorizedWordRequest;
import org.example.portfolio.user.adapter.in.dto.response.GetMemorizedWordResponse;
import org.example.portfolio.word.WordSteps;
import org.example.portfolio.word.application.port.out.WordRepository;
import org.example.portfolio.word.domain.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserApiTest extends ApiTest {

  @Autowired
  private SignRepository signRepository;

  @Autowired
  private WordRepository wordRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private TokenProvider tokenProvider;

  @BeforeEach
  void 회원가입() {
    SignUpRequest request = SignSteps.회원가입정보_생성();
    signRepository.save(User.from(request, passwordEncoder));
    wordRepository.save(new Word("word", "단어"));
  }

  @Test
  void 내가_외운_단어_등록() {
    String token = tokenProvider.createToken(String.format("%s:%s", 1, MemberType.ADMIN));
    final ExtractableResponse<Response> response = UserSteps.외운단어_체크(token);
    assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
  }

  @Test
  void 내가_외운_단어_조회() {
    String token = tokenProvider.createToken(String.format("%s:%s", 1, MemberType.ADMIN));
    UserSteps.외운단어_체크(token);
    final ExtractableResponse<Response> response = UserSteps.내가_외운_단어_조회(token);
    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.response().getBody().jsonPath().getList("mean").get(0)).isEqualTo("단어");
  }
}
