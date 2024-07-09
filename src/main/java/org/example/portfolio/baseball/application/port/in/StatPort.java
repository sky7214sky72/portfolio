package org.example.portfolio.baseball.application.port.in;

import java.util.List;
import org.example.portfolio.baseball.adapter.in.dto.StatGetResponseDto;

public interface StatPort {

  List<StatGetResponseDto> getHitterStat();
}
