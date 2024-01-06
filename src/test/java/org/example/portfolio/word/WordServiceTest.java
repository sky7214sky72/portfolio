package org.example.portfolio.word;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.example.portfolio.word.adapter.in.WordController;
import org.example.portfolio.word.adapter.in.dto.response.GetWordResponse;
import org.example.portfolio.word.application.service.WordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WordServiceTest {

  @Autowired
  private WordController wordController;

  @Test
  void 단어조회() {
    //단어 등록
    wordController.addWord(WordSteps.상품등록요청_생성());
    long wordId = 1L;
    //단어 조회
    final GetWordResponse response = wordController.getWord(wordId);
    //응답 검증
    assertThat(response).isNotNull();
    assertThat(response.word()).isEqualTo("word");
  }
}
