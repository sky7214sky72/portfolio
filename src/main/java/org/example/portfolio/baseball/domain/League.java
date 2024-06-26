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
public class League {

  @Id
  private String name;
  private int pa;
  private int ab;
  private int bb;
  private int ib;
  private int hp;
  private int sf;
  private int h;
  private int b2;
  private int b3;
  private int hr;
  private double obp;
  private int r;
}
