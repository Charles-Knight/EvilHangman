public class HangmanGamePlayer {

  public static void main(String[] args) {
    FairHangman game = new FairHangman("test", 3);
    System.out.println(game.isGameOver());

  }
}
