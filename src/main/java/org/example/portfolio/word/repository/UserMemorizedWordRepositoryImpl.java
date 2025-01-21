package org.example.portfolio.word.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.sign.domain.QUser;
import org.example.portfolio.word.domain.QUserMemorizedWord;
import org.example.portfolio.word.domain.QWord;
import org.example.portfolio.word.domain.dto.response.GetMemorizedWordResponse;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserMemorizedWordRepositoryImpl implements UserMemorizedWordCustom {

  private final JPAQueryFactory jpaQueryFactory;
  private final QUserMemorizedWord userMemorizedWord = QUserMemorizedWord.userMemorizedWord;
  private final QUser user = QUser.user;
  private final QWord word = QWord.word1;

  @Override
  public List<GetMemorizedWordResponse> getMemorizedWord(Long userId) {
    return jpaQueryFactory.from(userMemorizedWord)
        .join(user)
        .on(user.id.eq(userMemorizedWord.userId))
        .join(word)
        .on(word.id.eq(userMemorizedWord.wordId))
        .where(userMemorizedWord.userId.eq(userId))
        .select(
            Projections.constructor(GetMemorizedWordResponse.class, user.mail, word.id, word.word,
                word.mean))
        .fetch();
  }
}
