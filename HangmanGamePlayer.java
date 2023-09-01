import java.util.Scanner;

public class HangmanGamePlayer {

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    FairHangman game = new FairHangman("abc", 3);

    while (!game.isOver()) {
      System.out.println("Enter a letter: ");
      char guess = keyboard.next().toLowerCase().charAt(0);
      game.playerGuess(guess);
    }

    if (game.isWon()) {
      System.out.println("Game is over. You have won!");
    } else {
      System.out.println("Game is over. You have lost. : (");
    }

  }
}
