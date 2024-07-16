package org.example.portfolio.baseball.adapter.in.dto;

public record PitcherGetResponse(int fipPlus, int fipMinus, double fip, int eraPlus, int eraMinus,
                                 double era, double kNine, double bbNine, double homeRunNine) {

}
