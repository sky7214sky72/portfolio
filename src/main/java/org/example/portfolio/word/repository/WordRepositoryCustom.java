package org.example.portfolio.word.repository;

import org.example.portfolio.word.domain.dto.response.GetWordResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WordRepositoryCustom {

  Page<GetWordResponse> getWordList(String keyword, Long userId, Pageable pageable);
}
