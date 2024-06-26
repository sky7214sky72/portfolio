package org.example.portfolio.baseball.domain;

import org.example.portfolio.baseball.adapter.in.dto.WrcGetResponseDto;

public class WrcCalculator {

  /**
   * . 선수 wrc 계산
   */
  public static void wrc(League league, Team team, WrcGetResponseDto wrcGetResponseDto) {
    double hbi = (0.7 * wrcGetResponseDto.getHp()) + (0.7 * wrcGetResponseDto.getBb()) + (0.7
        * wrcGetResponseDto.getIb());
    double oneBaseHit = 0.9 * wrcGetResponseDto.getB1();
    double twoBaseHit = 1.25 * wrcGetResponseDto.getB2();
    double threeBaseHit = 1.6 * wrcGetResponseDto.getB3();
    double homeRun = 2 * wrcGetResponseDto.getHr();
    double abihs = wrcGetResponseDto.getAb() + wrcGetResponseDto.getBb() - wrcGetResponseDto.getIb()
        + wrcGetResponseDto.getHp() + wrcGetResponseDto.getSf();
    double hbiOneTwoThreeHr = hbi + oneBaseHit + twoBaseHit + threeBaseHit + homeRun;
    double wOba = hbiOneTwoThreeHr / abihs;
    double wObaSc = wrcGetResponseDto.getObp() / wOba;
    double leagueWoba = leagueWoba(league);
    double wRaa = ((wOba - leagueWoba) / wObaSc) * wrcGetResponseDto.getPa();
    double leagueNormalR = (double) league.getR() / league.getPa();
    double leagueWrcPa = (leagueNormalR / league.getPa()) * league.getPa();
    if (wrcGetResponseDto.getName().equals("김도영")) {
      System.out.println(wRaa);
      System.out.println(wObaSc);
      System.out.println(leagueWoba);
    }
    double wrc = ((wRaa / wrcGetResponseDto.getPa() + leagueNormalR) + (leagueNormalR
        - (leagueNormalR * team.getParkFactor()))) / leagueWrcPa * 100;
    wrcGetResponseDto.updateWrc((int) wrc);
  }

  private static double leagueWoba(League league) {
    int b1 = league.getH() - league.getB2() - league.getB3() - league.getHr();
    double hbi = (0.7 * league.getHp()) + (0.7 * league.getBb()) + (0.7
        * league.getIb());
    double oneBaseHit = 0.9 * b1;
    double twoBaseHit = 1.25 * league.getB2();
    double threeBaseHit = 1.6 * league.getB3();
    double homeRun = 2 * league.getHr();
    double abihs = league.getAb() + league.getBb() - league.getIb()
        + league.getHp() + league.getSf();
    double hbiOneTwoThreeHr = hbi + oneBaseHit + twoBaseHit + threeBaseHit + homeRun;
    return hbiOneTwoThreeHr / abihs;
  }
}
