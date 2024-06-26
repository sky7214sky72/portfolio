package org.example.portfolio.baseball.application.port.out;

import org.example.portfolio.baseball.domain.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, Long> {

}
