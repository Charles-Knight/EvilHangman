package com.github.charlesknight.overengineeredhangman;

public class GameWord {
  private WordPosition[] word;

  public GameWord(String word) {
    this.word = new WordPosition[word.length()];
    for (int i = 0; i < word.length(); i++) {
      this.word[i] = new WordPosition(word.charAt(i));
    }
  }

  public boolean positionIsFound(int position) {
    return this.word[position].isFound();
  }

  public int evaluate(char guess) {
    int positionsFound = 0;

    for (WordPosition pos : this.word) {
      if (pos.evaluate(guess)) {
        positionsFound++;
      }
    }
    return positionsFound;
  }

  public boolean isFound() {
    for (WordPosition pos : this.word) {
      if (!pos.isFound()) {
        return false;
      }
    }
    return true;
  }

  public String foundLetters() {
    String word = "";
    for (WordPosition pos : this.word) {
      if (pos.isFound()) {
        word = word + pos.value();
      } else {
        word = word + "_";
      }
    }
    return word;
  }

  public String toString() {
    String word = "";
    for (WordPosition pos : this.word) {
      word = word + pos.value();
    }
    return word;
  }
}
