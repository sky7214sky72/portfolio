package org.example.portfolio.word.adapter.out.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.word.domain.QWord;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
public class WordPredicate {

  private static final QWord word = QWord.word1;

  public static Predicate getWordListPredicate(String keyword) {
    BooleanBuilder builder = new BooleanBuilder();
    if (StringUtils.hasText(keyword)) {
      builder.and(word.word.like("%" + keyword + "%")).or(word.mean.like("%" + keyword + "%"));
    }
    return builder;
  }
}
