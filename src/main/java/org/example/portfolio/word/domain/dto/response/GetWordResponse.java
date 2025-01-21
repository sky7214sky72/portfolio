package org.example.portfolio.word.domain.dto.response;

import org.springframework.util.Assert;

public record GetWordResponse(long wordId, String word, String mean) {


  public GetWordResponse {
    Assert.notNull(word, "단어는 필수입니다.");
    Assert.notNull(mean, "뜻은 필수입니다.");
  }
}
