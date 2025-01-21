package org.example.portfolio.word.domain.dto.request;

import org.springframework.util.Assert;

public record AddWordRequest(String word, String mean) {

  public AddWordRequest {
    Assert.hasText(word, "단어는 필수 입니다.");
    Assert.hasText(mean, "뜻은 필수 입니다.");
  }
}
