package org.example.portfolio.baseball.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.baseball.service.StatService;
import org.example.portfolio.baseball.service.StatServiceImpl;
import org.example.portfolio.baseball.domain.dto.PitcherGetResponse;
import org.example.portfolio.baseball.domain.dto.StatGetResponseDto;
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
  public ResponseEntity<List<StatGetResponseDto>> getHitterStat() {
    final List<StatGetResponseDto> response = statService.getHitterStat();
    logger.info("Wrc+, Ops+ 조회");
    return ResponseEntity.ok(response.stream().sorted(Comparator.comparing(StatGetResponseDto::getWrcPlus).reversed()).toList());
  }

  @Operation(summary = "투수 성적 조회")
  @GetMapping("/pitcher")
  public ResponseEntity<List<PitcherGetResponse>> getPitcherStat() {
    final List<PitcherGetResponse> response = statService.getPitcherStat();
    logger.info("투수 스탯 조회");
    return ResponseEntity.ok(response.stream().sorted(Comparator.comparingDouble(PitcherGetResponse::fip)).toList());
  }
}
