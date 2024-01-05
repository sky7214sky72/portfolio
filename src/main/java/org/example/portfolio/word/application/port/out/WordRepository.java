package org.example.portfolio.word.application.port.out;

import org.example.portfolio.word.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WordRepository extends JpaRepository<Word, Long> {

}
