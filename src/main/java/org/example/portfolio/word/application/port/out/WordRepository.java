package org.example.portfolio.word.application.port.out;

import org.example.portfolio.word.domain.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface WordRepository extends JpaRepository<Word, Long>, WordRepositoryCustom {

}
