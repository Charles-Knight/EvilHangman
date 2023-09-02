package com.github.charlesknight;

public class WordPosition {
  private char character;
  private boolean found;

  public WordPosition(char character) {
    this.character = character;
    this.found = false;
  }

  public boolean evaluate(char guessedChar) {
    if (this.character == guessedChar) {
      this.found = true;
      return true;
    }
    return false;
  }

  public boolean isFound() {
    return this.found;
  }

  public char value() {
    return this.character;
  }
}
