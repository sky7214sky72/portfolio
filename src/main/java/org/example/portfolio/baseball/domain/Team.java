package org.example.portfolio.baseball.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "team", schema = "baseball")
@NoArgsConstructor
public class Team {

  private String teamName;
  @Id
  private String teamCode;
  private double parkFactor;
}
