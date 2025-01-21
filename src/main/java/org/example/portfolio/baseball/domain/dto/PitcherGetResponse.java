package org.example.portfolio.baseball.domain.dto;

public record PitcherGetResponse(String name, String team, double fipPlus, double fipMinus, double fip,
                                 int eraPlus, int eraMinus,
                                 double era, double kNine, double bbNine, double homeRunNine) {

}
