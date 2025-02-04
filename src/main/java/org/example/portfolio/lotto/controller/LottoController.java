package org.example.portfolio.lotto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.lotto.service.LottoService;
import org.example.portfolio.lotto.service.LottoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "로또")
@RestController
@RequestMapping("/lotto")
@RequiredArgsConstructor
public class LottoController {

  private final LottoService lottoService;

  @Operation(summary = "로또번호 맞추기")
  @GetMapping
  public void getPitcherStat() {
    lottoService.getLotto();
  }
}
