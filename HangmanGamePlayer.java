public class HangmanGamePlayer {

  public static void main(String[] args) {
    FairHangman game = new FairHangman("test", 3);

    while (!game.isOver()) {
      System.out.println("Game is still not over.");
      game.playerGuess('a');
    }

    if (game.isWon()) {
      System.out.println("Game is over. You have won!");
    } else {
      System.out.println("Game is over. You have lost. : (");
    }

  }
}
