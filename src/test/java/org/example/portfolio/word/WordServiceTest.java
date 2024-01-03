package org.example.portfolio.word;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WordServiceTest {

  private WordService wordService;
  private WordPort wordPort;
  private WordRepository wordRepository;

  @BeforeEach
  void setUp() {
    wordRepository = new WordRepository();
    wordPort = new WordAdapter(wordRepository);
    wordService = new WordService(wordPort);
  }

  @Test
  void 단어등록() {
    AddWordRequest addWordRequest = 상품등록요청_생성();
    wordService.addWord(addWordRequest);
  }

  private static AddWordRequest 상품등록요청_생성() {
    final String word = "word";
    final String mean = "단어";
    return new AddWordRequest(word, mean);
  }

}
