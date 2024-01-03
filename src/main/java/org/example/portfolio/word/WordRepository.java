package org.example.portfolio.word;

import java.util.HashMap;
import java.util.Map;

public class WordRepository {

  private Long sequence = 0L;
  private Map<Long, Word> persistence = new HashMap<>();

  public void save(final Word word) {
    word.assignId(++sequence);
    persistence.put(word.getId(), word);
  }
}
