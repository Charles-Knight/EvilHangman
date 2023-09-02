package com.github.charlesknight;

import java.util.HashSet;
import java.util.Set;

public class FairHangman {
  private GameWord gameWord;
  private int missedGuesses;
  private int maxMissedGuesses;
  private Set<Character> guessedLetters;
  private boolean gameOver;
  private boolean gameWon;

  public FairHangman(String word) {
    this(word, 7);
  }

  public FairHangman(String word, int maxMissedGuesses) {
    this.gameWord = new GameWord(word);
    this.maxMissedGuesses = maxMissedGuesses;
    this.missedGuesses = 0;
    this.guessedLetters = new HashSet<Character>();
    this.gameOver = false;
    this.gameWon = false;
  }

  public boolean playerGuess(char guess) {
    if (guessedLetters.contains(guess)) {
      return false;
    } else {
      guessedLetters.add(guess);
      int positionsFound = gameWord.evaluate(guess);
      if (positionsFound < 1) {
        missedGuesses++;
      }
      this.evaluateGameOver();
      return true;
    }
  }

  public int getMaxMissedGuesses() {
    return this.maxMissedGuesses;
  }

  public int getMissedGuesses() {
    return this.missedGuesses;
  }

  public String getGuessedLetters() {
    return this.guessedLetters.toString();
  }

  public String getCurrentWord() {
    if (this.isOver()) {
      return this.gameWord.toString();
    } else {
      return this.gameWord.foundLetters();
    }
  }

  public boolean isOver() {
    return this.gameOver;
  }

  public boolean isWon() {
    return this.gameWon;
  }

  private void evaluateGameOver() {
    if (gameWord.isFound()) {
      this.gameWon = true;
      this.gameOver = true;
    } else {
      this.gameOver = this.missedGuesses >= this.maxMissedGuesses;
    }
  }
}
