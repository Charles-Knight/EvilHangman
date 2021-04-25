import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

/* TODO: switch to hashtable that uses pattern as key with list of words as value.
 * To update, iterate over list and create new key for each word and sort in to
 * families by adding to list at key. Then find list with most entires and make
 * it the new list (list.size() should be O(1) operation). Get rid of smaller
 * lists.
 */
/* Hangman Class:
 * Represents the Hangman Game state and player actions
 */
public class Hangman{
    int wordLength;    // The length of the words in the list
    int maxGuesses;    // the max number of guesses allowed to the player
    int missedGuesses; // The number of guesses the player has failed
    String currentPattern;  // Current word family pattern
    Hashtable<String, ArrayList<String>> wordList; // Current list of words

    /* Variables that may make more sense in the App class 
     * boolean showCount;
     * boolean gameOver;
     */

    public Hangman(int _wordLength, int _maxGuesses) {
        this.wordLength = _wordLength;
        this.maxGuesses = _maxGuesses;
        this.missedGuesses = 0;       
        generateStartingList();
    }

    /* Generate the starting wordlist
     */
    private void generateStartingList() {
        // TODO: What is this magic number???
        // TODO: Figure out the maximum number of entries we need (26^wordLength) is max
        //       number of permutations possible
        this.wordList = new Hashtable<String, ArrayList<String>>(120000);
        String pattern = "";
        File dictionary = new File("dictionary.txt");
        Scanner inputFile = null;

        try {
            inputFile = new Scanner(dictionary);
        }
        catch(Exception E) {
            System.out.println("Something went wrong. Maybe the dictionary file is missing?");
        }

        //generate starting mask
        for(int i = 0; i < this.wordLength; i++)
            pattern = pattern + "-";

        // Read in words from dictionary file and if long enough
        // add to hashtable along with blank mask
        while(inputFile.hasNext()) {
            String word = inputFile.next();
            if(word.length() == this.wordLength)
                wordList.put(word, pattern);
        }

        this.currentPattern = pattern;
    }

    // TODO: Look in to FOR EACH loop to process hashtable
    /* Update the pattern stored for each entry in the wordList
     * 
     */
    private void updateWordPatterns(char c){
        Enumeration<String> words = this.wordList.keys();
        Enumeration<String> patterns = this.wordList.elements();

        while(words.hasMoreElements()){
            String word = words.nextElement();
            String pattern = patterns.nextElement();
            String newPattern = "";

            for(int i = 0; i < word.length(); i++){
                if(word.charAt(i) ==c){
                    newPattern = newPattern + c;
                } else {
                    newPattern = newPattern + pattern.charAt(i);
                }

                this.wordList.put(word, newPattern);
            }
        }
    }

    private void chooseNextPattern(){
        // TODO: Implement me.
    }
}