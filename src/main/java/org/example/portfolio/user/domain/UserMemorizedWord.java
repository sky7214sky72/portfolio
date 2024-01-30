package org.example.portfolio.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.portfolio.global.domain.BaseTimeEntity;

@Getter
@Entity
@Table(name = "user_memorized_word", schema = "english_study_web")
@NoArgsConstructor
public class UserMemorizedWord extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long userId;
  private Long wordId;

  public UserMemorizedWord(Long userId, Long wordId) {
    this.userId = userId;
    this.wordId = wordId;
  }
}
