package org.example.portfolio.sign.application.port.out;

import java.util.Optional;
import org.example.portfolio.sign.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignRepository extends JpaRepository<User, Long> {

  Optional<User> findByMail(String mail);

  boolean existsByMail(String mail);
}
