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
@Table(name = "pitcher", schema = "baseball")
@NoArgsConstructor
public class Pitcher {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(targetEntity = Team.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "team_code")
  private Team team;
  private String name;
  private int win;
  private int lose;
  private int save;
  private int game;
  private int er;
  private double ip;
  private int gs;
  private int so;
  private int bb;
  private int hp;
  private int ib;
  private int hit;
  private int hr;
}
