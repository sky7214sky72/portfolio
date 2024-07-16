package org.example.portfolio.baseball.application.port.out;

import org.example.portfolio.baseball.domain.LeaguePitcher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaguePitcherRepository extends JpaRepository<LeaguePitcher, Long> {

}
