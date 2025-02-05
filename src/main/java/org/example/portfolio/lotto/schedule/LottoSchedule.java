package org.example.portfolio.lotto.schedule;

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
    lottoService.getLotto();
  }

}
