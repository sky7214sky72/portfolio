package org.example.portfolio.word.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.util.Assert;

@Data
@Entity
public class Word {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String word;
  private String mean;

  public Word(String word, String mean) {
    Assert.hasText(word, "단어명은 필수입니다.");
    Assert.hasText(mean, "뜻은 필수입니다.");
    this.word = word;
    this.mean = mean;
  }

}
