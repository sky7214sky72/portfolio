package org.example.portfolio.word.application.port.out;

import org.example.portfolio.word.adapter.in.dto.response.GetWordResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WordRepositoryCustom {

  Page<GetWordResponse> getWordList(String keyword, Pageable pageable);
}
