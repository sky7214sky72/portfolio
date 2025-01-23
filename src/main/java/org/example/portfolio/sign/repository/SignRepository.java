package org.example.portfolio.sign.repository;

import java.util.Optional;
import org.example.portfolio.sign.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignRepository extends JpaRepository<User, Long> {

  Optional<User> findByMail(String mail);

  Optional<User> findByMailAndProvider(String mail, String provider);

  boolean existsByMail(String mail);
}
