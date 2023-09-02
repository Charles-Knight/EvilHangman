package com.github.charlesknight;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Enumeration;
import java.util.Random;
import java.io.File;

public class EvilHangmanApp {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    do {

      /*
       * Pre game set up, asks user for word size, number of failed guesses,
       * whether or not they would like to see current size of word list, and sets
       * up the starting word list.
       */

      int wordLength = getWordLength(); // Get word length
      int maxMisses = getGuesses(); // Set number of misses
      int misses = 0; // Number of missed guesses made by user.
      boolean showCount = setShowCount(); // Set word count display
      boolean gameOver = false;

      /*
       * Read in dictionary and store in Hashtable including only words of correct
       * length
       * Each word in our word list serves as a key in the dictionary and is mapped to
       * a masked version of itself. The masked words are what we will compare to
       * determine
       * word familys as we shrink our list.
       * Ex:
       * Cats -> ____
       * If the users inputs a letter (a) contained in Cats Key Value pair will be
       * updated to
       * Cats -> _a__
       * We can count number of times each word mask appears to determine the the size
       * of it's
       * family.
       */
      Hashtable<String, String> wordList = generateStartingList(wordLength);

      /*
       * Generate starting mask.
       * currentMask represents the currently selected word family. Since no guesses
       * have been made it will be n space characters, where n is the word length
       * input
       * by the user (A blank mask). As we compare word families we will update the
       * current mask to be
       * the largest family possible using the character entered by the user by
       * filling in
       * the characters at there respective locations.
       * If the word mask is narrowed down to one word then the user will have won.
       */
      String currentMask = "";
      for (int i = 0; i < wordLength; i++)
        currentMask = currentMask + "-";

      /*
       * Game loop to run through each turn. Displays current game state and prompts
       * user
       * for character input. User should input a single character but scanner will
       * accept
       * a whole string and just pull out the first character.
       * 
       * After accepting input the wordMasks in the word list are updated and the word
       * list
       * is passed to the chooseNewMask method to find all the distinct families and
       * count
       * their members. The word family with the highest number of elements is
       * chossen, and
       * then all words in the word list that do not match that mask are discarded.
       */

      while (!gameOver) {
        // printWordList(wordList); //Print entire word list for Testing purposes.
        // Print current game State
        System.out.println("\nWord: " + currentMask);
        System.out.println("Incorrect Guesses: " + misses + " out of " + maxMisses);
        if (showCount)
          System.out.println("Current size of word list: " + wordList.size());

        // Prompt user to input guess and read with scanner
        System.out.print("\nEnter guess: ");
        char guess = keyboard.next().toLowerCase().charAt(0);

        // Update masks in word list
        updateWordMasks(wordList, guess);
        // Choose new mask, and remove non-mactches
        String nextMask = chooseNewMask(wordList, currentMask);

        // We check to see if the next mask is the same as the currentMask
        // If so then we know that we chose a set whith no matches to the users
        // last input and will count this as a strike against the player.
        if (currentMask.equals(nextMask))
          misses++;
        currentMask = nextMask;

        // Check for win/lose
        // If the wordList has been reduced to one element and that element matches
        // the currentMask, then the user has won. Congraduate and set gameOver
        if (wordList.size() == 1 && wordList.containsKey(currentMask)) {
          System.out.println("You won!");
          gameOver = true;
        }
        // If the user has missed more guesses than their max allowed misses then
        // we know the player has lost. Let them know and set gameOver.
        if (misses >= maxMisses) {
          outputLoseMessage(wordList);
          gameOver = true;
        }
      }

      // Prompt for another game.
      System.out.print("Would you like to play again? ");
    } while (keyboard.next().toLowerCase().charAt(0) == 'y');

    keyboard.close();
  }

  private static int getWordLength() {
    int input = -1;
    Scanner keyboard = new Scanner(System.in);

    // Prompt user for word length and continue prompting until
    // a valid length is entered.
    while (input < 1 || input > 50) {
      System.out.println("Please enter a word length: ");
      input = keyboard.nextInt();
    }

    return input;
  }

  private static int getGuesses() {
    Scanner keyboard = new Scanner(System.in);
    int guesses = 0;

    while (guesses < 1) {
      System.out.print("How many failed guesses would you like: ");
      guesses = keyboard.nextInt();
    }
    return guesses;
  }

  /*
   * toggleShowCount
   * Prompts the user to enter if they would like to see a running count
   * off the number of words still in the word list.
   */
  private static boolean setShowCount() {
    Scanner keyboard = new Scanner(System.in);
    String input = "";

    // Continue prompting until "Y" or "N" is entered
    // Will also accept any word starting with "Y" as "Y"
    // or any word starting with "N" as "N"
    while (!(input.equals("Y") || input.equals("N"))) {
      System.out.print("Whould you like to see the number of words remaining in word list? ");
      input = keyboard.next();
      input = input.toUpperCase().substring(0, 1);
      // Convert input to upper case and get substring of just first character
    }

    if (input.equals("Y"))
      return true;
    else
      return false;
  }

  private static boolean toggleShowCount(boolean currentState) {
    return !currentState;
  }

  private static void outputLoseMessage(Hashtable<String, String> wordList) {
    String chosenWord = "";
    Enumeration<String> words = wordList.keys();
    Random picker = new Random();
    int index = picker.nextInt(wordList.size());

    for (int i = 0; i < index; i++)
      chosenWord = words.nextElement();

    System.out.println("Out of guesses.\nThe word was " + chosenWord);

  }

  /*
   * wordList methods for creating, editing, reading, and displaying word list
   */

  private static Hashtable<String, String> generateStartingList(int length) {
    Hashtable<String, String> wordList = new Hashtable<String, String>(120000);
    String mask = "";
    File dictionary = new File("dictionary.txt");
    Scanner inputFile = null;
    try {
      inputFile = new Scanner(dictionary);
    } catch (Exception E) {
      System.out.println("Something went wrong. Maybe the dictionary file is missing?");
    }

    // generate starting mask
    for (int i = 0; i < length; i++)
      mask = mask + "-";

    // Read in words from dictionary file and if long enough
    // add to hashtable along with blank mask
    while (inputFile.hasNext()) {
      String word = inputFile.next();
      if (word.length() == length)
        wordList.put(word, mask);
    }

    return wordList;
  }

  private static void printWordList(Hashtable<String, String> list) {
    Enumeration<String> keys = list.keys();
    Enumeration<String> vals = list.elements();
    while (keys.hasMoreElements())
      System.out.println(keys.nextElement() + " | " + vals.nextElement());
    System.out.println("Words in list: " + list.size());
  }

  /*
   * updateWordMasks iterates through wordlist using Enumerations and updates tthe
   * wordMasks by checking for selected character in the word (key) and unmasking
   * it
   * in the masked words (values). This will allow us to easily determine their
   * family
   * using the .equals method.
   */
  private static void updateWordMasks(Hashtable<String, String> list, char c) {
    Enumeration<String> words = list.keys();
    Enumeration<String> masks = list.elements();

    while (words.hasMoreElements()) {
      String word = words.nextElement();
      String wordMask = masks.nextElement();
      String newMask = "";

      // Iterate through word character by character and check if each position
      // contains the character input by user. If so add that character to newMask
      // otherwise add the character that was already wordMask.
      for (int i = 0; i < word.length(); i++) {
        if (word.charAt(i) == c)
          newMask = newMask + c;
        else
          newMask = newMask + wordMask.charAt(i);
      }

      // Update list with new masks
      list.put(word, newMask);

    }
  }

  private static String chooseNewMask(Hashtable<String, String> list, String currentMask) {
    // Find all word families and count elements
    Enumeration<String> masks = list.elements();
    ArrayList<String> families = new ArrayList<String>();
    int max = 0;
    String nextMask = "";

    while (masks.hasMoreElements()) {
      String wordMask = masks.nextElement();
      if (!families.contains(wordMask))
        families.add(wordMask);
    }

    for (int i = 0; i < families.size(); i++) {
      masks = list.elements();
      int count = 0;

      // Count wordMasks that equal family at i
      while (masks.hasMoreElements()) {
        if (masks.nextElement().equals(families.get(i)))
          count++;
      }

      // Output count for each word family. For testing purposes only.
      // System.out.println("Count for " + families.get(i) + " = " + count);

      // if count is higher than current max
      // update max and nexttMask
      if (count > max) {
        max = count;
        nextMask = families.get(i);
      }
    }

    // Remove non matched words
    Enumeration<String> words = list.keys();
    while (words.hasMoreElements()) {
      String word = words.nextElement();
      if (!list.get(word).equals(nextMask))
        list.remove(word);
    }

    return nextMask;
  }
}
