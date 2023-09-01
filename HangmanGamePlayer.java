import java.util.Scanner;

public class HangmanGamePlayer {

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    FairHangman game = new FairHangman("abc", 3);

    while (!game.isOver()) {
      System.out.println(game.getCurrentWord());
      System.out.println("Enter a letter: ");
      char guess = keyboard.next().toLowerCase().charAt(0);
      boolean guessAccepted = game.playerGuess(guess);
      if (!guessAccepted) {
        System.out.println("You already played that letter.");
      }
    }

    if (game.isWon()) {
      System.out.println("Congratulations! You have won!");
    } else {
      System.out.println("Game Over. You have lost. The word was " + game.getCurrentWord() + ".");
    }

  }
}
