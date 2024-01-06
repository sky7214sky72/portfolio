package org.example.portfolio.word.application.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.word.adapter.in.dto.request.AddWordRequest;
import org.example.portfolio.word.adapter.in.dto.response.GetWordResponse;
import org.example.portfolio.word.application.port.in.WordPort;
import org.example.portfolio.word.application.port.out.WordRepository;
import org.example.portfolio.word.domain.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class WordService implements WordPort {

  private final WordRepository wordRepository;

  @Override
  public void save(List<AddWordRequest> requestList) {
    List<Word> wordList = new ArrayList<>();
    requestList.forEach(list -> {
      final Word word = new Word(list.word(), list.mean());
      wordList.add(word);
    });
    wordRepository.saveAll(wordList);
  }

  @Override
  public Word getWord(long wordId) {
    return wordRepository.findById(wordId)
        .orElseThrow(() -> new IllegalArgumentException("단어가 존재하지 않습니다."));
  }

  @Override
  public Page<GetWordResponse> getWordList(String keyword, int page, int size) {
    return StringUtils.hasText(keyword) ? wordRepository.findByWordOrMean(keyword,
        PageRequest.of(page, size)).map(this::mapToResponse) : wordRepository.findAll(PageRequest.of(page, size)).map(this::mapToResponse);
  }

  private GetWordResponse mapToResponse(Word word) {
    return new GetWordResponse(word.getId(), word.getWord(), word.getMean());
  }
}
