package org.example.portfolio.lotto.application.out;

import org.example.portfolio.lotto.domain.Lotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LottoRepository extends JpaRepository<Lotto, Long> {

}
