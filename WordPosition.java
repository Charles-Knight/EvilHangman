public class WordPosition {
  private char character;
  private boolean found;

  public WordPosition(char character) {
    this.character = character;
    this.found = false;
  }

  public void evaluate(char guessedChar) {
    this.found = this.character == guessedChar ? true : false;
  }

  public boolean isFound() {
    return this.found;
  }

  public char value() {
    return this.character;
  }
}
