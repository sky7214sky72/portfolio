package org.example.portfolio.user.application.port.out;

import org.example.portfolio.user.domain.UserMemorizedWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMemorizedWordRepository extends JpaRepository<UserMemorizedWord, Long> {

}
