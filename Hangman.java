import java.io.File;
import java.util.Hashtable;
import java.util.Scanner;


public class Hangman{
    int wordLength;
    int maxGuesses;
    int usedGuesses;
    Hashtable<String, String> wordList;

    /* Variables that may make more sense in the App class 
     * boolean showCount;
     * boolean gameOver;
     */

    public Hangman(int _wordLength, int _maxGuesses){
        this.wordLength = _wordLength;
        this.maxGuesses = _maxGuesses;
        this.usedGuesses = 0;
        
        this.wordList = generateStartingList(wordLength);

    }

    /* Generate the starting wordlist
     */
    private void generateStartingList(){
        // TODO: What is this magic number???
        this.wordList = new Hashtable<String, String>(120000);
        String pattern = "";
        File dictionary = new File("dictionary.txt");
        Scanner inputFile = null;

        try{
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
    }




}