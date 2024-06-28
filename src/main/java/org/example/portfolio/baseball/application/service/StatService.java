package org.example.portfolio.baseball.application.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.baseball.adapter.in.dto.WrcGetResponseDto;
import org.example.portfolio.baseball.application.port.in.StatPort;
import org.example.portfolio.baseball.application.port.out.HitterRepository;
import org.example.portfolio.baseball.application.port.out.LeagueRepository;
import org.example.portfolio.baseball.domain.Hitter;
import org.example.portfolio.baseball.domain.League;
import org.example.portfolio.baseball.domain.WrcCalculator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatService implements StatPort {

  private final LeagueRepository leagueRepository;
  private final HitterRepository hitterRepository;

  @Override
  public List<WrcGetResponseDto> getHitterStat() {
    List<Hitter> hitters = hitterRepository.findAll();
    League league = leagueRepository.findAll().get(0);
    List<WrcGetResponseDto> result = new ArrayList<>();
    hitters.forEach(hitter -> {
      WrcGetResponseDto wrcGetResponseDto = new WrcGetResponseDto();
      wrcGetResponseDto.entityMapper(hitter);
      WrcCalculator.wrc(league, hitter.getTeam(), wrcGetResponseDto);
      result.add(wrcGetResponseDto);
    });
    return result;
  }
}
