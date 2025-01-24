package org.example.portfolio.baseball.domain;

import org.example.portfolio.baseball.domain.dto.PitcherGetResponse;

public class PitcherStatCalculator {

  private PitcherStatCalculator() {
    throw new IllegalStateException("Utility class");
  }

  public static PitcherGetResponse getResponse(Pitcher pitcher, LeaguePitcher leaguePitcher) {
    double c = getC(leaguePitcher);

    // FIP 관련 계산
    double pitcherF = calculateFIP(pitcher);
    double leaguePitcherF = calculateFIP(leaguePitcher);
    double fip = calculateRoundedFIP(pitcherF, c);
    double leagueFip = calculateRoundedFIP(leaguePitcherF, c);
    double fipPlus = calculateFIPPlus(fip, leagueFip, pitcher);
    double fipMinus = calculateFIPMinus(fipPlus);

    // ERA 관련 계산
    double era = calculateERA(pitcher);
    double leagueEra = calculateERA(leaguePitcher);
    int eraPlus = calculateERAPlus(era, leagueEra, pitcher);
    int eraMinus = (int) (100 * (100 / (100 * (leagueEra / (era + (era - era * pitcher.getTeam().getParkFactor()))))));
    // K/BB/HR 관련 계산
    double kNine = calculateK9(pitcher);
    double bbNine = calculateBB9(pitcher);
    double homeRunNine = calculateHomeRun9(pitcher);

    return new PitcherGetResponse(
        pitcher.getName(),
        pitcher.getTeam().getTeamName(),
        Math.round(fipPlus),
        Math.round(fipMinus),
        fip,
        eraPlus,
        eraMinus,
        era,
        kNine,
        bbNine,
        homeRunNine
    );
  }

  private static double getC(LeaguePitcher leaguePitcher) {
    double hrBbHpId = getHrBbHpId(leaguePitcher);
    double er = getEr(leaguePitcher);
    double hr = getHr(leaguePitcher);
    double so = getSo(leaguePitcher);
    return Math.round(
        (er - hr - hrBbHpId + so) / leaguePitcher.getIp() * 100.0) / 100.0;
  }

  private static double getSo(LeaguePitcher leaguePitcher) {
    return 2 * leaguePitcher.getSo();
  }

  private static double getHr(LeaguePitcher leaguePitcher) {
    return 13 * leaguePitcher.getHr();
  }

  private static double getHrBbHpId(LeaguePitcher leaguePitcher) {
    return 3 * (leaguePitcher.getBb() + leaguePitcher.getHp() - leaguePitcher.getIb());
  }

  private static double getEr(LeaguePitcher leaguePitcher) {
    return 9 * leaguePitcher.getEr();
  }

  // FIP 계산
  private static double calculateFIP(Pitcher pitcher) {
    return (-(2 * pitcher.getSo()) + (3 * (pitcher.getBb() + pitcher.getHp())) + (13
        * pitcher.getHr())) / pitcher.getIp();
  }

  private static double calculateFIP(LeaguePitcher leaguePitcher) {
    return
        (-(2 * leaguePitcher.getSo()) + (3 * (leaguePitcher.getBb() + leaguePitcher.getHp())) + (13
            * leaguePitcher.getHr())) / leaguePitcher.getIp();
  }

  private static double calculateRoundedFIP(double fip, double c) {
    return Math.round((fip + c) * 100.0) / 100.0;
  }

  private static double calculateFIPPlus(double fip, double leagueFip, Pitcher pitcher) {
    return 100 * (leagueFip / (fip + (fip - fip * pitcher.getTeam().getParkFactor())));
  }

  private static double calculateFIPMinus(double fipPlus) {
    return 100 * (100 / fipPlus);
  }

  // ERA 계산
  private static double calculateERA(Pitcher pitcher) {
    return Math.round(pitcher.getEr() / pitcher.getIp() * 9 * 100.0) / 100.0;
  }

  private static double calculateERA(LeaguePitcher leaguePitcher) {
    return Math.round(leaguePitcher.getEr() / leaguePitcher.getIp() * 9 * 100.0) / 100.0;
  }

  private static int calculateERAPlus(double era, double leagueEra, Pitcher pitcher) {
    return (int) (100 * (leagueEra / (era + (era - era * pitcher.getTeam().getParkFactor()))));
  }

  private static int calculateERAMinus(int eraPlus) {
    return (int) (100 * (100 / eraPlus));
  }

  // K9, BB9, HR9 계산
  private static double calculateK9(Pitcher pitcher) {
    return Math.round(pitcher.getSo() / pitcher.getIp() * 9 * 100.0) / 100.0;
  }

  private static double calculateBB9(Pitcher pitcher) {
    return Math.round(pitcher.getBb() / pitcher.getIp() * 9 * 100.0) / 100.0;
  }

  private static double calculateHomeRun9(Pitcher pitcher) {
    return Math.round(pitcher.getHr() / pitcher.getIp() * 9 * 100.0) / 100.0;
  }
}
