package org.example.portfolio.baseball.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.portfolio.baseball.domain.Hitter;
import org.example.portfolio.baseball.domain.HitterEntity;
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

  public void entityMapper(HitterEntity entity) {
    this.pa = entity.getPa();
    this.ab = entity.getAb();
    this.bb = entity.getBb();
    this.ib = entity.getIb();
    this.hp = entity.getHp();
    this.sf = entity.getSf();
    this.h = entity.getH();
    this.b1 = entity.getH() - entity.getB2() - entity.getB3() - entity.getHr();
    this.b2 = entity.getB2();
    this.b3 = entity.getB3();
    this.hr = entity.getHr();
    this.obp = entity.getObp();
    if (entity instanceof Hitter hitter) {
      this.teamName = hitter.getTeam().getTeamName();
      this.name = hitter.getName();
    } else if (entity instanceof League league) {
      this.name = league.getName();
      this.r = league.getR();
    }
  }

  public void updateWrcPlus(int wrcPlus) {
    this.wrcPlus = wrcPlus;
  }

  public void updateOpsPlus(double opsPlus) {
    this.opsPlus = opsPlus;
  }
}
