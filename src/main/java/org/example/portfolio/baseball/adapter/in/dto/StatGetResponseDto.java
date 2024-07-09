package org.example.portfolio.baseball.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
  @JsonIgnore
  private int pa;
  @JsonIgnore
  private int ab;
  @JsonIgnore
  private int bb;
  @JsonIgnore
  private int ib;
  @JsonIgnore
  private int hp;
  @JsonIgnore
  private int sf;
  @JsonIgnore
  private int h;
  @JsonIgnore
  private int b1;
  @JsonIgnore
  private int b2;
  @JsonIgnore
  private int b3;
  @JsonIgnore
  private int hr;
  @JsonIgnore
  private double obp;
  private int wrcPlus;
  private double opsPlus;
  @JsonIgnore
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
