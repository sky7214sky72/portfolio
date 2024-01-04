package org.example.portfolio.word.adapter.in;

import lombok.RequiredArgsConstructor;
import org.example.portfolio.word.adapter.in.dto.AddWordRequest;
import org.example.portfolio.word.application.service.WordService;
import org.example.portfolio.word.domain.Word;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/words")
@RequiredArgsConstructor
public class WordController {

  private final WordService wordService;

  @PostMapping
  public ResponseEntity<Void> addWord(@RequestBody final AddWordRequest addWordRequest) {
    final Word word = new Word(addWordRequest.word(), addWordRequest.mean());
    wordService.save(word);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
