package org.example.portfolio.word.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.portfolio.global.domain.BaseTimeEntity;
import org.springframework.util.Assert;

@Getter
@Entity
@Table(name = "word", schema = "english_study_web")
@NoArgsConstructor
public class Word extends BaseTimeEntity {

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
