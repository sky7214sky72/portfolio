package org.example.portfolio.word.application.service;

import lombok.RequiredArgsConstructor;
import org.example.portfolio.word.application.port.in.WordPort;
import org.example.portfolio.word.application.port.out.WordRepository;
import org.example.portfolio.word.domain.Word;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WordService implements WordPort {

  private final WordRepository wordRepository;

  @Override
  public void save(final Word word) {
    wordRepository.save(word);
  }

  @Override
  public Word getWord(long wordId) {
    return wordRepository.findById(wordId).orElseThrow(() -> new IllegalArgumentException("단어가 존재하지 않습니다."));
  }
}
