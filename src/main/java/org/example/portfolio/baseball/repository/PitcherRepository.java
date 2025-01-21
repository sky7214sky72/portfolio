package org.example.portfolio.baseball.repository;

import org.example.portfolio.baseball.domain.Pitcher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PitcherRepository extends JpaRepository<Pitcher, Long> {

}
