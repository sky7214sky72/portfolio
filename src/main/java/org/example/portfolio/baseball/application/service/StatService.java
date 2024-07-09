package org.example.portfolio.baseball.application.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.baseball.adapter.in.dto.StatGetResponseDto;
import org.example.portfolio.baseball.application.port.in.StatPort;
import org.example.portfolio.baseball.application.port.out.HitterRepository;
import org.example.portfolio.baseball.application.port.out.LeagueRepository;
import org.example.portfolio.baseball.domain.Hitter;
import org.example.portfolio.baseball.domain.League;
import org.example.portfolio.baseball.domain.OpsCalculator;
import org.example.portfolio.baseball.domain.WrcCalculator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatService implements StatPort {

  private final LeagueRepository leagueRepository;
  private final HitterRepository hitterRepository;

  @Override
  public List<StatGetResponseDto> getHitterStat() {
    List<Hitter> hitters = hitterRepository.findAll();
    League league = leagueRepository.findAll().get(0);
    List<StatGetResponseDto> result = new ArrayList<>();
    hitters.forEach(hitter -> {
      StatGetResponseDto statGetResponseDto = new StatGetResponseDto();
      statGetResponseDto.entityMapper(hitter);
      StatGetResponseDto leagueGetResponseDto = new StatGetResponseDto();
      leagueGetResponseDto.entityMapper(league);
      WrcCalculator.wrcPlus(leagueGetResponseDto, hitter.getTeam(), statGetResponseDto);
      OpsCalculator.opsPlus(leagueGetResponseDto, hitter.getTeam(), statGetResponseDto);
      result.add(statGetResponseDto);
    });
    return result;
  }
}
