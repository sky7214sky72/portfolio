package org.example.portfolio.word;

class WordService {

  private final WordPort wordPort;

  WordService(final WordPort wordPort) {
    this.wordPort = wordPort;
  }

  public void addWord(final AddWordRequest addWordRequest) {
    final Word word = new Word(addWordRequest.word(), addWordRequest.mean());
    wordPort.save(word);
  }
}
