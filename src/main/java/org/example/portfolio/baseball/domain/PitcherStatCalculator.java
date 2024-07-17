package org.example.portfolio.baseball.domain;

import org.example.portfolio.baseball.adapter.in.dto.PitcherGetResponse;

public class PitcherStatCalculator {

  public static PitcherGetResponse getResponse(Pitcher pitcher, LeaguePitcher leaguePitcher) {
    double c = Math.round(
        (9 * leaguePitcher.getEr() - 13 * leaguePitcher.getHr() - 3 * (leaguePitcher.getBb()
            + leaguePitcher.getHp() - leaguePitcher.getIb()) + 2 * leaguePitcher.getSo())
            / leaguePitcher.getIp() * 100.0) / 100.0;
    double pitcherF = (-(2 * pitcher.getSo()) + (3 * (pitcher.getBb() + pitcher.getHp())) + (13
        * pitcher.getHr())) / pitcher.getIp();
    double leaguePitcherF =
        (-(2 * leaguePitcher.getSo()) + (3 * (leaguePitcher.getBb() + leaguePitcher.getHp())) + (13
            * leaguePitcher.getHr())) / leaguePitcher.getIp();
    if (pitcher.getName().equals("윌커슨")) {
      System.out.println(c);
      System.out.println(pitcherF);
    }
    double leagueFip = Math.round((leaguePitcherF + c) * 100.0) / 100.0;
    double fip = Math.round((pitcherF + c) * 100.0) / 100.0;
    double fipPlus = (100 * (leagueFip / (fip + (fip - fip * pitcher.getTeam()
        .getParkFactor()))));
    double fipMinus = (100 * (100 / (100 * (leagueFip / (fip + (fip - fip * pitcher.getTeam()
            .getParkFactor()))))));
    double leagueEra = Math.round(leaguePitcher.getEr() / leaguePitcher.getIp() * 9 * 100.0) / 100.0;
    double era = Math.round(pitcher.getEr() / pitcher.getIp() * 9 * 100.0) / 100.0;
    int eraPlus = (int) (100 * (leagueEra / (era + (era - era * pitcher.getTeam()
        .getParkFactor()))));
    int eraMinus = (int) (100 * (100 / (100 * (leagueEra / (era + (era - era * pitcher.getTeam()
        .getParkFactor()))))));
    double kNine = Math.round(pitcher.getSo() / pitcher.getIp() * 9 * 100.0) / 100.0;
    double bbNine = Math.round(pitcher.getBb() / pitcher.getIp() * 9 * 100.0) / 100.0;
    double homeRunNine = Math.round(pitcher.getHr() / pitcher.getIp() * 9 * 100.0) / 100.0;
    return new PitcherGetResponse(pitcher.getName(), pitcher.getTeam().getTeamName(), Math.round(fipPlus),
        Math.round(fipMinus), fip, eraPlus, eraMinus, era, kNine, bbNine,
        homeRunNine);
  }
}
