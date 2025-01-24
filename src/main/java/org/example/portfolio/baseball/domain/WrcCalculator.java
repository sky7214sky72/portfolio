package org.example.portfolio.baseball.domain;

import org.example.portfolio.baseball.domain.dto.StatGetResponseDto;

public class WrcCalculator {

  /**
   * . woba 계산
   */
  private static double wOba(StatGetResponseDto statGetResponseDto) {
    double hbi = calculateHbi(statGetResponseDto);
    double oneBaseHit = 0.9 * statGetResponseDto.getB1();
    double twoBaseHit = 1.25 * statGetResponseDto.getB2();
    double threeBaseHit = 1.6 * statGetResponseDto.getB3();
    double homeRun = 2 * statGetResponseDto.getHr();
    double abihs = calculateAbihs(statGetResponseDto);
    double hbiOneTwoThreeHr = hbi + oneBaseHit + twoBaseHit + threeBaseHit + homeRun;
    return hbiOneTwoThreeHr / abihs;
  }

  private static double calculateHbi(StatGetResponseDto statGetResponseDto) {
    return (0.7 * statGetResponseDto.getHp()) + (0.7 * statGetResponseDto.getBb()) - (0.7
        * statGetResponseDto.getIb());
  }

  private static double calculateAbihs(StatGetResponseDto statGetResponseDto) {
    return statGetResponseDto.getAb() + statGetResponseDto.getBb() - statGetResponseDto.getIb()
        + statGetResponseDto.getHp() + statGetResponseDto.getSf();
  }

  /**
   * . 선수 wrcPlus 계산
   */
  public static void wrcPlus(StatGetResponseDto leagueGetResponseDto, Team team,
      StatGetResponseDto statGetResponseDto) {
    double wOba = wOba(statGetResponseDto);
    double wObaSc = calculateWObaSc(statGetResponseDto, wOba);
    double leagueWoba = wOba(leagueGetResponseDto);
    double wRaa = calculateWRaa(wOba, leagueWoba, wObaSc, statGetResponseDto);
    double leagueNormalR = calculateLeagueNormalR(leagueGetResponseDto);
    double leagueWrcPa = calculateLeagueWrcPa(leagueGetResponseDto, leagueNormalR);
    double wrc = calculateWrc(wRaa, statGetResponseDto, leagueNormalR, leagueWrcPa, team);
    statGetResponseDto.updateWrcPlus((int) wrc);
  }

  private static double calculateWObaSc(StatGetResponseDto statGetResponseDto, double wOba) {
    return statGetResponseDto.getObp() / wOba * 1.17;
  }

  private static double calculateWRaa(double wOba, double leagueWoba, double wObaSc,
      StatGetResponseDto statGetResponseDto) {
    return ((wOba - leagueWoba) / wObaSc) * statGetResponseDto.getPa();
  }

  private static double calculateLeagueNormalR(StatGetResponseDto leagueGetResponseDto) {
    return (double) leagueGetResponseDto.getR() / leagueGetResponseDto.getPa();
  }

  private static double calculateLeagueWrcPa(StatGetResponseDto leagueGetResponseDto,
      double leagueNormalR) {
    return (leagueNormalR / leagueGetResponseDto.getPa()) * leagueGetResponseDto.getPa();
  }

  private static double calculateWrc(double wRaa, StatGetResponseDto statGetResponseDto,
      double leagueNormalR, double leagueWrcPa, Team team) {
    return ((wRaa / statGetResponseDto.getPa() + leagueNormalR) + (leagueNormalR
        - (leagueNormalR * team.getParkFactor()))) / leagueWrcPa * 100;
  }
}
