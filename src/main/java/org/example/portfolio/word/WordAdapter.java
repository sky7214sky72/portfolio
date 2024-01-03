package org.example.portfolio.word;

class WordAdapter implements WordPort {

  private final WordRepository wordRepository;

  public WordAdapter(WordRepository wordRepository) {
    this.wordRepository = wordRepository;
  }

  @Override
  public void save(final Word word) {
    wordRepository.save(word);
  }
}
