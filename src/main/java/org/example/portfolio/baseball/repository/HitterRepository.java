package org.example.portfolio.baseball.repository;

import org.example.portfolio.baseball.domain.Hitter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HitterRepository extends JpaRepository<Hitter, Long> {

}
