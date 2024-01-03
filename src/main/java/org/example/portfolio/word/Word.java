package org.example.portfolio.word;

import lombok.Data;
import org.springframework.util.Assert;

@Data
public class Word {

  private Long id;
  private final String word;
  private final String mean;

  public Word(String word, String mean) {
    Assert.hasText(word, "단어명은 필수입니다.");
    Assert.hasText(mean, "뜻은 필수입니다.");
    this.word = word;
    this.mean = mean;
  }

  public void assignId(Long id) {
    this.id = id;
  }

}
