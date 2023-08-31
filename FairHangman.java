import java.io.File;
import java.util.Scanner;

public class FairHangman {
  private GameWord gameWord;
  private int missedGuesses;
  private int maxMissedGuesses;
  private boolean gameOver;
  private boolean gameWon;

  public FairHangman(String word, int maxMissedGuesses) {
    this.gameWord = new GameWord(word);
    this.maxMissedGuesses = maxMissedGuesses;
    this.missedGuesses = 0;
    this.gameOver = false;
    this.gameWon = false;
  }

  public void playerGuess(char guess) {
    System.out.println("This feature not yet implemented. Guess NOT recorded.");
    this.missedGuesses++;
    this.evaluateGameOver();
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
