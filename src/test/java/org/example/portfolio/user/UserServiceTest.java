package org.example.portfolio.user;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.example.portfolio.global.domain.MemberType;
import org.example.portfolio.global.jwt.TokenProvider;
import org.example.portfolio.sign.SignSteps;
import org.example.portfolio.sign.adapter.in.dto.request.SignUpRequest;
import org.example.portfolio.sign.application.port.out.SignRepository;
import org.example.portfolio.sign.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class UserServiceTest {

  @Autowired
  TokenProvider tokenProvider;

  @Autowired
  SignRepository signRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @BeforeEach
  void 회원가입() {
    SignUpRequest request = SignSteps.회원가입정보_생성();
    signRepository.save(User.from(request, passwordEncoder));
  }

  @Test
  void 내가_외운_단어_등록() {
    String token = tokenProvider.createToken(String.format("%s:%s", 1, MemberType.ADMIN));
    Long userId = tokenProvider.getTokenSubject(token);
    Long wordId = 1L;
    MemorizedWordRequest memorizedWordRequest = new MemorizedWordRequest(userId, wordId);
    UserService userService = new UserService();
    ResponseEntity<UserMemorizedWord> result = userService.memorizedWord(memorizedWordRequest);
    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(result.getBody().userId).isEqualTo(1L);
  }

  private interface UserPort {

    ResponseEntity<UserMemorizedWord> memorizedWord(MemorizedWordRequest memorizedWordRequest);
  }


  private class UserService implements UserPort {

    @Override
    public ResponseEntity<UserMemorizedWord> memorizedWord(MemorizedWordRequest memorizedWordRequest) {
      UserMemorizedWord userMemorizedWord = new UserMemorizedWord(1L, memorizedWordRequest.userId(),
          memorizedWordRequest.wordId());
      UserMemorizedWordRepository userMemorizedWordRepository = new UserMemorizedWordRepository();
      UserMemorizedWord result = userMemorizedWordRepository.save(userMemorizedWord);
      return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
  }

  private class UserMemorizedWord {

    private Long id;
    private Long userId;
    private Long wordId;

    public UserMemorizedWord(Long id, Long userId, Long wordId) {
      this.id = id;
      this.userId = userId;
      this.wordId = wordId;
    }
  }

  private class UserMemorizedWordRepository {

    public UserMemorizedWord save(UserMemorizedWord userMemorizedWord) {
      return new UserMemorizedWord(userMemorizedWord.id, userMemorizedWord.userId,
          userMemorizedWord.wordId);
    }
  }
}
