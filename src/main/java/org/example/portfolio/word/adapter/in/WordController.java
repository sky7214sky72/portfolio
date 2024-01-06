package org.example.portfolio.word.adapter.in;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.word.adapter.in.dto.request.AddWordRequest;
import org.example.portfolio.word.adapter.in.dto.response.GetWordResponse;
import org.example.portfolio.word.application.service.WordService;
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

@RestController
@RequestMapping("/words")
@RequiredArgsConstructor
public class WordController {

  private final WordService wordService;

  @PostMapping
  @Transactional
  public ResponseEntity<Void> addWord(@RequestBody final List<AddWordRequest> addWordRequest) {
    wordService.save(addWordRequest);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/{wordId}")
  public ResponseEntity<GetWordResponse> getWord(@PathVariable final long wordId) {
    final Word word = wordService.getWord(wordId);
    final GetWordResponse response = new GetWordResponse(
        word.getId(),
        word.getWord(),
        word.getMean()
    );
    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<Page<GetWordResponse>> getWordList(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(required = false) String keyword) {
    final Page<GetWordResponse> response = wordService.getWordList(keyword, page, size);
    return ResponseEntity.ok(response);
  }
}
