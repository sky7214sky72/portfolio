package org.example.portfolio.word.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.global.annotation.AllAuthorize;
import org.example.portfolio.global.jwt.TokenProvider;
import org.example.portfolio.word.domain.dto.request.MemorizedWordRequest;
import org.example.portfolio.word.domain.dto.response.GetMemorizedWordResponse;
import org.example.portfolio.word.service.UserServiceImpl;
import org.example.portfolio.word.domain.UserMemorizedWord;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 기능")
@RestController
@RequestMapping("/word/memorized")
@RequiredArgsConstructor
public class UserController {

  private final UserServiceImpl userService;
  private final TokenProvider tokenProvider;

  @Operation(summary = "외운 단어 체크")
  @AllAuthorize
  @PostMapping(value = "/{wordId}")
  @Transactional
  public ResponseEntity<UserMemorizedWord> memorizedWord(HttpServletRequest request, @PathVariable Long wordId) {
    Long userId = tokenProvider.getTokenSubject(request.getHeader("Authorization").replace("Bearer ", ""));
    return userService.memorizedWord(new MemorizedWordRequest(userId, wordId));
  }

  @Operation(summary = "외운 단어 조회")
  @AllAuthorize
  @GetMapping
  public ResponseEntity<List<GetMemorizedWordResponse>> getMemorizedWord(HttpServletRequest request) {
    Long userId = tokenProvider.getTokenSubject(request.getHeader("Authorization").replace("Bearer ", ""));
    return userService.getMemorizedWord(userId);
  }
}
