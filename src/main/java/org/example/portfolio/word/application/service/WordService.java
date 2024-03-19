package org.example.portfolio.word.application.service;

import static org.example.portfolio.global.domain.ErrorCode.ALREADY_USER_SAVE;
import static org.example.portfolio.global.domain.ErrorCode.USER_NOT_FOUND;
import static org.example.portfolio.global.domain.ErrorCode.WORD_NOT_FOUND;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.global.domain.ApiResponse;
import org.example.portfolio.global.exception.CustomException;
import org.example.portfolio.global.jwt.TokenProvider;
import org.example.portfolio.sign.application.port.out.SignRepository;
import org.example.portfolio.sign.domain.User;
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
  private final TokenProvider tokenProvider;
  private final SignRepository signRepository;

  @Override
  public ResponseEntity<Object> save(HttpServletRequest request, List<AddWordRequest> requestList) {
    List<Word> wordList = new ArrayList<>();
    User user = getUserByToken(request);
    requestList.forEach(list -> {
      final Word word = new Word(list.word(), user, list.mean());
      if (!wordRepository.existsByWordAndMeanAndUser(list.word(), list.mean(), user)) {
        wordList.add(word);
      }
    });
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(wordRepository.saveAll(wordList));
  }

  @Override
  public Word getWord(HttpServletRequest request, long wordId) {
    User user = getUserByToken(request);
    Word response = wordRepository.findById(wordId)
        .orElseThrow(() -> new CustomException(WORD_NOT_FOUND));
    if (!Objects.equals(response.getUser().getId(), user.getId())) {
      throw new CustomException(WORD_NOT_FOUND);
    }
    return response;
  }

  @Override
  public Page<GetWordResponse> getWordList(HttpServletRequest request, String keyword, int page, int size) {
    User user = getUserByToken(request);
    return wordRepository.getWordList(keyword, user.getId(), PageRequest.of(page, size));
  }

  private User getUserByToken(HttpServletRequest request) {
    Long userId = tokenProvider.getTokenSubject(
        request.getHeader("Authorization").replace("Bearer ", ""));
    return signRepository.findById(userId)
        .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
  }
}
