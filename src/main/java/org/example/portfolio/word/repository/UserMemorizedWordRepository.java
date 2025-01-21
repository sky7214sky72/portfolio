package org.example.portfolio.word.repository;

import org.example.portfolio.word.domain.UserMemorizedWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMemorizedWordRepository extends JpaRepository<UserMemorizedWord, Long>,
    UserMemorizedWordCustom {

}
