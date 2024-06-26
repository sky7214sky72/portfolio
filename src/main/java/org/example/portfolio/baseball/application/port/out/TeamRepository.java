package org.example.portfolio.baseball.application.port.out;

import org.example.portfolio.baseball.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
