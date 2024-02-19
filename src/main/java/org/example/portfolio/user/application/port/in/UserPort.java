package org.example.portfolio.user.application.port.in;

import java.util.List;
import org.example.portfolio.user.adapter.in.dto.request.MemorizedWordRequest;
import org.example.portfolio.user.adapter.in.dto.response.GetMemorizedWordResponse;
import org.example.portfolio.user.domain.UserMemorizedWord;
import org.springframework.http.ResponseEntity;

public interface UserPort {

  ResponseEntity<UserMemorizedWord> memorizedWord(MemorizedWordRequest memorizedWordRequest);

  ResponseEntity<List<GetMemorizedWordResponse>> getMemorizedWord(Long userId);
}
