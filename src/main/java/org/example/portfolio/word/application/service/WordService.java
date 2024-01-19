package org.example.portfolio.word.application.service;

import static org.example.portfolio.global.domain.ErrorCode.WORD_NOT_FOUND;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.global.domain.ApiResponse;
import org.example.portfolio.global.exception.CustomException;
import org.example.portfolio.word.adapter.in.dto.request.AddWordRequest;
import org.example.portfolio.word.adapter.in.dto.response.GetWordResponse;
import org.example.portfolio.word.application.port.in.WordPort;
import org.example.portfolio.word.application.port.out.WordRepository;
import org.example.portfolio.word.domain.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WordService implements WordPort {

  private final WordRepository wordRepository;

  @Override
  public ResponseEntity<Object> save(List<AddWordRequest> requestList) {
    List<Word> wordList = new ArrayList<>();
    requestList.forEach(list -> {
      final Word word = new Word(list.word(), list.mean());
      if (!wordRepository.existsByWordAndMean(list.word(), list.mean())) {
        wordList.add(word);
      }
    });
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(wordRepository.saveAll(wordList));
  }

  @Override
  public Word getWord(long wordId) {
    return wordRepository.findById(wordId)
        .orElseThrow(() -> new CustomException(WORD_NOT_FOUND));
  }

  @Override
  public Page<GetWordResponse> getWordList(String keyword, int page, int size) {
    return wordRepository.getWordList(keyword, PageRequest.of(page, size));
  }
}
