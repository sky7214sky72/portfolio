package org.example.portfolio.baseball.domain;

import org.example.portfolio.baseball.adapter.in.dto.PitcherGetResponse;

public class PitcherStatCalculator {

  public static PitcherGetResponse getResponse(Pitcher pitcher, LeaguePitcher leaguePitcher) {
    double c = (9 * leaguePitcher.getEr() - 13 * leaguePitcher.getHr() - 3 * (leaguePitcher.getBb()
        + leaguePitcher.getHp() * leaguePitcher.getIb()) + 2 * leaguePitcher.getSo())
        / leaguePitcher.getIp();
    double pitcherF = (-(2 * pitcher.getSo()) + (3 * (pitcher.getBb() + pitcher.getHp())) + (13
        * pitcher.getHr())) / pitcher.getIp();
    double leaguePitcherF =
        (-(2 * leaguePitcher.getSo()) + (3 * (leaguePitcher.getBb() + leaguePitcher.getHp())) + (13
            * leaguePitcher.getHr())) / leaguePitcher.getIp();
    double leagueFip = leaguePitcherF + c;
    double fip = pitcherF + c;
    int fipPlus = (int) (100 * (leagueFip / (fip + (fip - fip * pitcher.getTeam()
        .getParkFactor()))));
    int fipMinus = 100 * (100 / fipPlus);
    double leagueEra = leaguePitcher.getEr() / leaguePitcher.getIp() * 9;
    double era = pitcher.getEr() / pitcher.getIp() * 9;
    int eraPlus = (int) (100 * (leagueEra / (era + (era - era * pitcher.getTeam()
        .getParkFactor()))));
    int eraMinus = 100 * (100 / eraPlus);
    double kNine = pitcher.getSo() / pitcher.getIp() * 9;
    double bbNine = pitcher.getBb() / pitcher.getIp() * 9;
    double homeRunNine = pitcher.getHr() / pitcher.getIp() * 9;
    return new PitcherGetResponse(fipPlus, fipMinus, fip, eraPlus, eraMinus, era, kNine, bbNine,
        homeRunNine);
  }
}
