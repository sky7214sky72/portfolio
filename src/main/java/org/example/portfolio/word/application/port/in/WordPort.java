package org.example.portfolio.word.application.port.in;

import org.example.portfolio.word.domain.Word;

public interface WordPort {

  void save(final Word word);
}
