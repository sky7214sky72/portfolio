package org.example.portfolio.baseball.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "hitter", schema = "baseball")
@NoArgsConstructor
public class Hitter extends HitterEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(targetEntity = Team.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "team_code")
  private Team team;
  private String name;
}
