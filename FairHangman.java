import java.io.File;
import java.util.Scanner;

public class FairHangman {
  private GameWord gameWord;
  private int missedGuesses;
  private int maxMissedGuesses;
  private boolean gameOver;

  public FairHangman(String word, int maxMissedGuesses) {
    this.gameWord = new GameWord(word);
    this.maxMissedGuesses = maxMissedGuesses;
    this.missedGuesses = 0;
    this.gameOver = false;
  }

  public boolean isGameOver() {
    return this.gameOver;
  }

}
