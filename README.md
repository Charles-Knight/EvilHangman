# Overengineered Hangman
An overengineered set of Hangman games that server as a simple set of programs to experiment with programming in Java.

## Fair Hangman
A simple Hangman game, inspired by the original project, which will be used to experiment with and refresh on programming in Java.

## Evil Hangman
The original project stored here was a version of the game in which the computer cheats and it is genearlly impossible for the player to win. This was created as a simple class project for CSIS 10B and will likely not see any serious development.

The computer makes use of a large word list that it filters in to classes based on the players input. As the player guesses letters, the computer creates classes based on the particular position(s) in wich the letter could fall in the word and groups words with letters in the same position(s) in to a singular class. Words with zero occurences of the user input are also a class. The computer then selects the class with the greatest size and uses that as it's updated word pool. If selected class contains the input at some position, then the computer awards that guess to the player, otherwise the player is exhausts one of their allowed incorrect guesses and the game continues. Once the player has exhausted all of their guesses the computer selects a random word from the remaining word pool to be the word that it "selected"

