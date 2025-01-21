package org.example.portfolio.word.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.global.annotation.AllAuthorize;
import org.example.portfolio.word.domain.dto.request.AddWordRequest;
import org.example.portfolio.word.domain.dto.response.GetWordResponse;
import org.example.portfolio.word.service.WordServiceImpl;
import org.example.portfolio.word.domain.Word;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "단어 조회 및 단어 등록")
@RestController
@RequestMapping("/word")
@RequiredArgsConstructor
public class WordController {

  private final WordServiceImpl wordService;

  @Operation(summary = "단어등록")
  @AllAuthorize
  @PostMapping
  @Transactional
  public ResponseEntity<Void> addWord(HttpServletRequest request,
      @RequestBody final List<AddWordRequest> addWordRequest) {
    wordService.save(request, addWordRequest);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Operation(summary = "단어 상세 조회")
  @GetMapping("/{wordId}")
  public ResponseEntity<GetWordResponse> getWord(HttpServletRequest request,
      @PathVariable final long wordId) {
    final Word word = wordService.getWord(request, wordId);
    final GetWordResponse response = new GetWordResponse(
        word.getId(),
        word.getWord(),
        word.getMean()
    );
    return ResponseEntity.ok(response);
  }

  @Operation(summary = "단어조회")
  @GetMapping
  public ResponseEntity<Page<GetWordResponse>> getWordList(
      HttpServletRequest request,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(required = false) String keyword) {
    final Page<GetWordResponse> response = wordService.getWordList(request, keyword, page, size);
    return ResponseEntity.ok(response);
  }
}
