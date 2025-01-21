package org.example.portfolio.word.repository;

import java.util.List;
import org.example.portfolio.word.domain.dto.response.GetMemorizedWordResponse;

public interface UserMemorizedWordCustom {

  List<GetMemorizedWordResponse> getMemorizedWord(Long userId);
}
