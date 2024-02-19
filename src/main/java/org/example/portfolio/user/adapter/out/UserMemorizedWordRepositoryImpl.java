package org.example.portfolio.user.adapter.out;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.sign.domain.QUser;
import org.example.portfolio.user.adapter.in.dto.response.GetMemorizedWordResponse;
import org.example.portfolio.user.application.port.out.UserMemorizedWordCustom;
import org.example.portfolio.user.domain.QUserMemorizedWord;
import org.example.portfolio.word.domain.QWord;
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
            Projections.constructor(GetMemorizedWordResponse.class, user.name, word.id, word.word,
                word.mean))
        .fetch();
  }
}
