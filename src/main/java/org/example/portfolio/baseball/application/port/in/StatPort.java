package org.example.portfolio.baseball.application.port.in;

import java.util.List;
import org.example.portfolio.baseball.adapter.in.dto.WrcGetResponseDto;

public interface StatPort {

  List<WrcGetResponseDto> getHitterStat();
}
