package org.example.portfolio.baseball.adapter.in.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.portfolio.baseball.domain.Hitter;
import org.example.portfolio.baseball.domain.League;

@Getter
@Setter
@NoArgsConstructor
public class StatGetResponseDto {

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
  private int wrcPlus;
  private double opsPlus;
  private int r;

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

  public void entityMapper(League league) {
    this.name = league.getName();
    this.pa = league.getPa();
    this.ab = league.getAb();
    this.bb = league.getBb();
    this.ib = league.getIb();
    this.hp = league.getHp();
    this.sf = league.getSf();
    this.h = league.getH();
    this.b1 = league.getH() - league.getB2() - league.getB3() - league.getHr();
    this.b2 = league.getB2();
    this.b3 = league.getB3();
    this.hr = league.getHr();
    this.obp = league.getObp();
    this.r = league.getR();
  }

  public void updateWrcPlus(int wrcPlus) {
    this.wrcPlus = wrcPlus;
  }

  public void updateOpsPlus(double opsPlus) {
    this.opsPlus = opsPlus;
  }
}
