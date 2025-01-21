package org.example.portfolio.word.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.sign.domain.QUser;
import org.example.portfolio.word.domain.dto.response.GetWordResponse;
import org.example.portfolio.word.repository.predicate.WordPredicate;
import org.example.portfolio.word.domain.QWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class WordRepositoryImpl implements WordRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;
  private final QWord word = QWord.word1;
  private static final QUser user = QUser.user;

  @Override
  public Page<GetWordResponse> getWordList(String keyword, Long userId, Pageable pageable) {
    Predicate predicate = WordPredicate.getWordListPredicate(keyword, userId);
    List<GetWordResponse> content = jpaQueryFactory
        .select(Projections.constructor(GetWordResponse.class, word.id, word.word, word.mean))
        .from(word)
        .join(user).on(word.user.id.eq(user.id))
        .where(predicate)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();
    JPAQuery<Long> countQuery = jpaQueryFactory.select(word.count())
        .from(word).where(predicate);
    return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
  }
}
