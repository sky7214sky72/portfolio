package org.example.portfolio.baseball.domain;

import org.example.portfolio.baseball.adapter.in.dto.WrcGetResponseDto;

public class WrcCalculator {

  /**
   * . woba 계산
   */
  private static double wOba(WrcGetResponseDto wrcGetResponseDto) {
    double hbi = (0.7 * wrcGetResponseDto.getHp()) + (0.7 * wrcGetResponseDto.getBb()) + (0.7
        * wrcGetResponseDto.getIb());
    double oneBaseHit = 0.9 * wrcGetResponseDto.getB1();
    double twoBaseHit = 1.25 * wrcGetResponseDto.getB2();
    double threeBaseHit = 1.6 * wrcGetResponseDto.getB3();
    double homeRun = 2 * wrcGetResponseDto.getHr();
    double abihs = wrcGetResponseDto.getAb() + wrcGetResponseDto.getBb() - wrcGetResponseDto.getIb()
        + wrcGetResponseDto.getHp() + wrcGetResponseDto.getSf();
    double hbiOneTwoThreeHr = hbi + oneBaseHit + twoBaseHit + threeBaseHit + homeRun;
    return hbiOneTwoThreeHr / abihs;
  }
  /**
   * . 선수 wrcPlus 계산
   */
  public static void wrcPlus(WrcGetResponseDto leaguewrcGetResponseDto, Team team, WrcGetResponseDto wrcGetResponseDto) {
    double wOba = wOba(wrcGetResponseDto);
    double wObaSc = wrcGetResponseDto.getObp() / wOba;
    double leagueWoba = wOba(leaguewrcGetResponseDto);
    double wRaa = ((wOba - leagueWoba) / wObaSc) * wrcGetResponseDto.getPa();
    double leagueNormalR = (double) leaguewrcGetResponseDto.getR() / leaguewrcGetResponseDto.getPa();
    double leagueWrcPa = (leagueNormalR / leaguewrcGetResponseDto.getPa()) * leaguewrcGetResponseDto.getPa();
    double wrc = ((wRaa / wrcGetResponseDto.getPa() + leagueNormalR) + (leagueNormalR
        - (leagueNormalR * team.getParkFactor()))) / leagueWrcPa * 100;
    wrcGetResponseDto.updateWrcPlus((int) wrc);
  }
}
