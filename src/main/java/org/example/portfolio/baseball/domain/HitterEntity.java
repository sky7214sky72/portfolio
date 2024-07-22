package org.example.portfolio.baseball.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class HitterEntity {
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
}
