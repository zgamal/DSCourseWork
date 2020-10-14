/* 
 * THOUGHT QUESTIONS
 *
 * 1. True
 *
 * 2. Subtract 1 from the sum before diving by 7 and taking the remainder. 
 * 
 * 3. A leap year means that there is February 29th. This will  affect the day of the week for dates that are after February 29th, since
 * there is an unusual extra day that was unaccounted for. The formula is adjusted so that it accounts for that shift for days after February
 * 29th. However, if the day is before that it hadn't yet and so we subtract 1. 
 * 
 * 4. 3 mod 7
 */

import java.io.Console;
import java.util.Arrays; 
import java.util.Scanner;

public class Hangman {
	
	public static void main(String[] args) {
		
		// Scanner stuff
		
		System.out.println("Welcome to the ASCII Version of Hangman!");
		Console c = System.console();
		Scanner s = new Scanner(System.in);
		char[] letters;
		String prompt = "Please enter a secret word: ";
		if(c != null) {
			letters = c.readPassword(prompt);
			for(int i=0; i<letters.length; i++) {
				letters[i] = Character.toUpperCase(letters[i]);
			}	
		} else {
			System.out.println("For best results, please run this from the command line.");
			System.out.print(prompt);
			letters = s.nextLine().trim().toUpperCase().toCharArray();
			for(int i=0; i<10000; i++) System.out.println();
		}
		
		Gallows g  = new Gallows(); // initializes gallows
		int found = 0; // keeps track of whether letter guessed was found in secret word
		int solved = 0; // keeps count of letters solved
		char[] answer = new char[letters.length * 2]; // to store placeholder for guess
		
		// creates the placeholder for the guess
		for (int i = 0; i < letters.length * 2; i +=2) {
			answer[i] = '_';
			answer[i+1] = ' ';
		}
		
		// prints gallows
		System.out.println(g);
		
		// plays the game
		while (g.isAlive() && solved < letters.length) {
			System.out.print("Puzzle to solve: ");
			System.out.println(answer);
			System.out.print("Please guess a letter: ");
			char guess = s.nextLine().toUpperCase().charAt(0);
			found = 0;
			System.out.println(guess);
			
			// checks if guess is in secret word by iterating over all letters and updates answer, solved, and found accordingly
			for (int i = 0; i < letters.length; i++) {
				if (letters[i] == guess) {	
					
					// check if answer was not already updated
					if (answer[2*i] != guess) {
						answer[2*i] = guess;
						solved++;
					}
					found = 1;
				} 
			} 
			
			// hangs if guess is not found in secret word and reprints gallows
			if (found != 1) { 
				g.hang();
				System.out.println(g);
			}
		}
		
		// Prints the results of the game
		if (solved < letters.length) {
			System.out.println("Game over! Player 1 wins!");
		} else {
			System.out.println("Success!  Player 2 wins!");
		}
	} 	
}
