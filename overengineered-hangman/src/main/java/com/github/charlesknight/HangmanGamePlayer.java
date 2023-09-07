package com.github.charlesknight;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class HangmanGamePlayer {

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    FairHangman game = new FairHangman(selectWord());

    while (!game.isOver()) {
      System.out.println("Word: " + game.getCurrentWord() + " - Errors:" + game.getMissedGuesses() + "/"
          + game.getMaxMissedGuesses() + " - Guesses: " + game.getGuessedLetters());
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

    keyboard.close();
  }

  // TODO: Better handle selection of word, input of dictionary file, etc...
  private static String selectWord() {
    int minWordSize = 7;
    Set<String> wordList = new HashSet<String>();
    InputStream dictionaryFile = HangmanGamePlayer.class.getResourceAsStream("/dictionary.txt");

    try {
      Scanner inputFile = new Scanner(dictionaryFile);
      while (inputFile.hasNext()) {
        String word = inputFile.next();
        if (word.length() >= minWordSize) {
          wordList.add(word);
        }
      }
      int selectedIndex = new Random().nextInt(wordList.size());
      inputFile.close();
      return wordList.toArray()[selectedIndex].toString();
    } catch (Exception E) {
      System.out.println("Something went wrong. Perhaps the dictionary file is missing.");
    }
    // Add real error handling?
    // On error the string is error
    return "error";
  }
}
