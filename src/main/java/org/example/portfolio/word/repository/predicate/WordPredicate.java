package org.example.portfolio.word.repository.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.sign.domain.QUser;
import org.example.portfolio.word.domain.QWord;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
public class WordPredicate {

  private static final QWord word = QWord.word1;
  private static final QUser user = QUser.user;

  public static Predicate getWordListPredicate(String keyword, Long userId) {
    BooleanBuilder builder = new BooleanBuilder();
    builder.and(user.id.eq(userId));
    if (StringUtils.hasText(keyword)) {
      builder.and(word.word.like("%" + keyword + "%")).or(word.mean.like("%" + keyword + "%"));
    }
    return builder;
  }
}
