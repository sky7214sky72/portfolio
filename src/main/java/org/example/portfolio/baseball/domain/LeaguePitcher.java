package org.example.portfolio.baseball.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "league_pitcher", schema = "baseball")
@NoArgsConstructor
public class LeaguePitcher {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int so;
  private int bb;
  private int hp;
  private int ib;
  private int hr;
  private int er;
  private double ip;
}
