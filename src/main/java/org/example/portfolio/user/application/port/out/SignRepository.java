package org.example.portfolio.user.application.port.out;

import java.util.Optional;
import org.example.portfolio.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignRepository extends JpaRepository<User, Long> {

  Optional<User> findByMail(String mail);
}
