package org.example.portfolio.word.service;

import java.util.List;
import org.example.portfolio.word.domain.dto.request.MemorizedWordRequest;
import org.example.portfolio.word.domain.dto.response.GetMemorizedWordResponse;
import org.example.portfolio.word.domain.UserMemorizedWord;
import org.springframework.http.ResponseEntity;

public interface UserService {

  ResponseEntity<UserMemorizedWord> memorizedWord(MemorizedWordRequest memorizedWordRequest);

  ResponseEntity<List<GetMemorizedWordResponse>> getMemorizedWord(Long userId);
}
