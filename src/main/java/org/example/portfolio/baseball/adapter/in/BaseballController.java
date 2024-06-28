package org.example.portfolio.baseball.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.baseball.adapter.in.dto.WrcGetResponseDto;
import org.example.portfolio.baseball.application.service.StatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "야구")
@RestController
@RequestMapping("/baseball")
@RequiredArgsConstructor
public class BaseballController {

  private final Logger logger = LoggerFactory.getLogger(BaseballController.class);
  private final StatService statService;

  @Operation(summary = "타자 성적 조회")
  @GetMapping("/hitter")
  public ResponseEntity<List<WrcGetResponseDto>> getHitterStat() {
    final List<WrcGetResponseDto> response = statService.getHitterStat();
    logger.info("Wrc+ 조회");
    return ResponseEntity.ok(response.stream().sorted(Comparator.comparing(WrcGetResponseDto::getWrc).reversed()).toList());
  }
}
