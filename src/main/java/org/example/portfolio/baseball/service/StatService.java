package org.example.portfolio.baseball.service;

import java.util.List;
import org.example.portfolio.baseball.domain.dto.PitcherGetResponse;
import org.example.portfolio.baseball.domain.dto.StatGetResponseDto;

public interface StatService {

  List<StatGetResponseDto> getHitterStat();

  List<PitcherGetResponse> getPitcherStat();
}
