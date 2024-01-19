package org.example.portfolio.word.application.port.in;

import java.util.List;
import org.example.portfolio.global.domain.ApiResponse;
import org.example.portfolio.word.adapter.in.dto.request.AddWordRequest;
import org.example.portfolio.word.adapter.in.dto.response.GetWordResponse;
import org.example.portfolio.word.domain.Word;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface WordPort {

  ResponseEntity<Object> save(final List<AddWordRequest> requestList);

  Word getWord(long wordId);

  Page<GetWordResponse> getWordList(String keyword, int page, int size);
}
