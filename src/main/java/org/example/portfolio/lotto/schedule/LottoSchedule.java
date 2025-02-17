package org.example.portfolio.lotto.schedule;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.lotto.service.LottoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LottoSchedule {

  private final LottoService lottoService;

  /**
   * . 매주 월요일 실행
   */
  @Scheduled(cron = "0 0 * * 1")
  public void lottoSchedule() {
    lottoService.getLotto(calculateCurrentWeekNumber());
  }

  private int calculateCurrentWeekNumber() {
    LocalDate startDate = LocalDate.of(2002, 12, 7); // 로또 제1회 추첨일
    LocalDate today = LocalDate.now();
    return (int) ChronoUnit.WEEKS.between(startDate, today) + 1;
  }
}
