package org.example.portfolio.lotto.repository;

import java.util.List;
import org.example.portfolio.lotto.domain.Lotto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LottoRepository extends JpaRepository<Lotto, Long> {

  /**
   * 모든 당첨 번호 조회
   */
  @Query("SELECT l.first, l.second, l.third, l.forth, l.fifth, l.sixth FROM Lotto l")
  List<Object[]> findAllWinningNumbers();

  /**
   * 최근 N회차 당첨 번호 조회
   */
  @Query("SELECT l.first, l.second, l.third, l.forth, l.fifth, l.sixth FROM Lotto l ORDER BY l.drawNumber DESC")
  List<Object[]> findRecentWinningNumbers(Pageable pageable);
}
