package org.example.portfolio.word.repository;

import org.example.portfolio.sign.domain.User;
import org.example.portfolio.word.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long>, WordRepositoryCustom {

  boolean existsByWordAndMeanAndUser(String word, String mean, User user);
}
