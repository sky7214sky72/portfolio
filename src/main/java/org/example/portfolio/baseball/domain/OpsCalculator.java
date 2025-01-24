package org.example.portfolio.baseball.domain;

import org.example.portfolio.baseball.domain.dto.StatGetResponseDto;

public class OpsCalculator {

  private OpsCalculator() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * OPS+ : (선수출루율 / 리그출루율 + 선수장타율/리그장타율-1) * 100/파크팩터) 장타율 : (1루타*1 + 2루타 *2 + 3루타 *3 + 홈런 *4) /
   * 타수
   */
  public static void opsPlus(StatGetResponseDto leagueGetResponseDto, Team team,
      StatGetResponseDto statGetResponseDto) {
    double slg = getSlg(statGetResponseDto);
    double leagueSlg = getLeagueSlg(leagueGetResponseDto);
    double opsPlus = getOpsPlus(slg, leagueSlg, statGetResponseDto, leagueGetResponseDto, team);
    statGetResponseDto.updateOpsPlus(Math.round(opsPlus * 1000.0) / 1000.0);
  }

  private static double getSlg(StatGetResponseDto statGetResponseDto) {
    return (double) (statGetResponseDto.getB1() + statGetResponseDto.getB2() * 2
        + statGetResponseDto.getB3() * 3 + statGetResponseDto.getHr() * 4)
        / statGetResponseDto.getAb();
  }

  private static double getLeagueSlg(StatGetResponseDto leagueGetResponseDto) {
    return (double) (leagueGetResponseDto.getB1() + leagueGetResponseDto.getB2() * 2
        + leagueGetResponseDto.getB3() * 3 + leagueGetResponseDto.getHr() * 4)
        / leagueGetResponseDto.getAb();
  }

  private static double getOpsPlus(double slg, double leagueSlg,
      StatGetResponseDto statGetResponseDto, StatGetResponseDto leagueGetResponseDto, Team team) {

    double obpRatio = statGetResponseDto.getObp() / leagueGetResponseDto.getObp();
    double slgRatio = roundToThreeDecimalPlaces(slg) / roundToThreeDecimalPlaces(leagueSlg);

    return (obpRatio + slgRatio - 1) * 100 / team.getParkFactor();
  }

  private static double roundToThreeDecimalPlaces(double value) {
    return Math.round(value * 1000.0) / 1000.0;
  }
}
