package org.example.portfolio.user.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.user.adapter.in.dto.request.MemorizedWordRequest;
import org.example.portfolio.user.adapter.in.dto.response.GetMemorizedWordResponse;
import org.example.portfolio.user.application.port.in.UserPort;
import org.example.portfolio.user.application.port.out.UserMemorizedWordRepository;
import org.example.portfolio.user.domain.UserMemorizedWord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserPort {

  private final UserMemorizedWordRepository userMemorizedWordRepository;

  @Override
  public ResponseEntity<UserMemorizedWord> memorizedWord(
      MemorizedWordRequest memorizedWordRequest) {
    UserMemorizedWord userMemorizedWord = new UserMemorizedWord(memorizedWordRequest.userId(),
        memorizedWordRequest.wordId());
    UserMemorizedWord result = userMemorizedWordRepository.save(userMemorizedWord);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @Override
  public ResponseEntity<List<GetMemorizedWordResponse>> getMemorizedWord(Long userId) {
    List<GetMemorizedWordResponse> result = userMemorizedWordRepository.getMemorizedWord(userId);
    return ResponseEntity.status(HttpStatus.OK)
        .body(result);
  }
}
