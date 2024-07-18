package org.example.portfolio.baseball.domain;

import org.example.portfolio.baseball.adapter.in.dto.StatGetResponseDto;

public class WrcCalculator {

  /**
   * . woba 계산
   */
  private static double wOba(StatGetResponseDto statGetResponseDto) {
    double hbi = (0.7 * statGetResponseDto.getHp()) + (0.7 * statGetResponseDto.getBb()) - (0.7
        * statGetResponseDto.getIb());
    double oneBaseHit = 0.9 * statGetResponseDto.getB1();
    double twoBaseHit = 1.25 * statGetResponseDto.getB2();
    double threeBaseHit = 1.6 * statGetResponseDto.getB3();
    double homeRun = 2 * statGetResponseDto.getHr();
    double abihs = statGetResponseDto.getAb() + statGetResponseDto.getBb() - statGetResponseDto.getIb()
        + statGetResponseDto.getHp() + statGetResponseDto.getSf();
    double hbiOneTwoThreeHr = hbi + oneBaseHit + twoBaseHit + threeBaseHit + homeRun;
    return hbiOneTwoThreeHr / abihs;
  }
  /**
   * . 선수 wrcPlus 계산
   */
  public static void wrcPlus(StatGetResponseDto leagueGetResponseDto, Team team, StatGetResponseDto statGetResponseDto) {
    double wOba = wOba(statGetResponseDto);
    double wObaSc = statGetResponseDto.getObp() / wOba * 1.17;
    double leagueWoba = wOba(leagueGetResponseDto);
    double wRaa = ((wOba - leagueWoba) / wObaSc) * statGetResponseDto.getPa();
    double leagueNormalR = (double) leagueGetResponseDto.getR() / leagueGetResponseDto.getPa();
    double leagueWrcPa = (leagueNormalR / leagueGetResponseDto.getPa()) * leagueGetResponseDto.getPa();
    double wrc = ((wRaa / statGetResponseDto.getPa() + leagueNormalR) + (leagueNormalR
        - (leagueNormalR * team.getParkFactor()))) / leagueWrcPa * 100;
    statGetResponseDto.updateWrcPlus((int) wrc);
  }
}
