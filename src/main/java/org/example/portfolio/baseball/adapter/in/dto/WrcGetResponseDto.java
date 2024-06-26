package org.example.portfolio.baseball.adapter.in.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.portfolio.baseball.domain.Hitter;

@Getter
@Setter
@NoArgsConstructor
public class WrcGetResponseDto {

  private String teamName;
  private String name;
  private int pa;
  private int ab;
  private int bb;
  private int ib;
  private int hp;
  private int sf;
  private int h;
  private int b1;
  private int b2;
  private int b3;
  private int hr;
  private double obp;
  private int wrc;

  public void entityMapper(Hitter hitter) {
    this.teamName = hitter.getTeam().getTeamName();
    this.name = hitter.getName();
    this.pa = hitter.getPa();
    this.ab = hitter.getAb();
    this.bb = hitter.getBb();
    this.ib = hitter.getIb();
    this.hp = hitter.getHp();
    this.sf = hitter.getSf();
    this.h = hitter.getH();
    this.b1 = hitter.getH() - hitter.getB2() - hitter.getB3() - hitter.getHr();
    this.b2 = hitter.getB2();
    this.b3 = hitter.getB3();
    this.hr = hitter.getHr();
    this.obp = hitter.getObp();
  }

  public void updateWrc(int wrc) {
    this.wrc = wrc;
  }
}
