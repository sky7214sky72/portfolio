package org.example.portfolio.baseball.domain;

import org.example.portfolio.baseball.adapter.in.dto.StatGetResponseDto;

public class OpsCalculator {

  /**
   * OPS+ : (선수출루율 / 리그출루율 + 선수장타율/리그장타율-1) * 100/파크팩터) 장타율 : (1루타*1 + 2루타 *2 + 3루타 *3 + 홈런 *4) /
   * 타수
   */
  public static void opsPlus(StatGetResponseDto leagueGetResponseDto, Team team,
      StatGetResponseDto statGetResponseDto) {
    double slg = (double) (statGetResponseDto.getB1() + statGetResponseDto.getB2() * 2
        + statGetResponseDto.getB3() * 3 + statGetResponseDto.getHr() * 4)
        / statGetResponseDto.getAb();
    double leagueSlg = (double) (leagueGetResponseDto.getB1() + leagueGetResponseDto.getB2() * 2
        + leagueGetResponseDto.getB3() * 3 + leagueGetResponseDto.getHr() * 4)
        / leagueGetResponseDto.getAb();
    double opsPlus = (statGetResponseDto.getObp() / leagueGetResponseDto.getObp() + slg / leagueSlg -1) * 100 / team.getParkFactor();
        statGetResponseDto.updateOpsPlus(opsPlus);
    statGetResponseDto.updateOpsPlus(opsPlus);
  }
}
