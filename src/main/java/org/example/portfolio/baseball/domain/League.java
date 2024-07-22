package org.example.portfolio.baseball.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "league", schema = "baseball")
@NoArgsConstructor
public class League extends HitterEntity {

  @Id
  private String name;
  private int r;
}
