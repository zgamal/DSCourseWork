import java.io.Console;
import java.util.Scanner;

public class Extentions {
	
	// function that checks if letter b is in array a, makes code simpler 
	public static boolean doesContain(char[] a, char b) {
		int contain = 1;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == b) {
				contain = 0;
			}
		}
		if (contain == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		
		// Scanner stuff
		System.out.println("Welcome to the ASCII Version of Hangman!");
		Console c = System.console();
		Scanner s = new Scanner(System.in);
		char[] letters;
		char mode; // stores game mode
		WordList list;
		String prompt = "Please enter a secret word: ";
		if(c != null) {
			
			// Asks for game mode 
			System.out.print("Please enter game mode. 0 for computer player, 1 for single player, and 2 for multiplayer: ");
			mode = s.nextLine().charAt(0);
			
			letters = c.readPassword(prompt);
			for(int i=0; i<letters.length; i++) {
				letters[i] = Character.toUpperCase(letters[i]);
			}	
		} else {
			
			// asks for game mode
			System.out.print("Please enter game mode. 0 for computer player, 1 for single player, and 2 for multiplayer: ");
			mode = s.nextLine().charAt(0);
			
			System.out.println("For best results, please run this from the command line.");
			System.out.print(prompt);
			letters = s.nextLine().trim().toUpperCase().toCharArray();

			for(int i=0; i<10000; i++) System.out.println();
		}
		
		Gallows g  = new Gallows(); // initializes gallows
		int found = 0; // keeps track of whether letter guessed was found in secret word
		int solved = 0; // keeps count of letters solved
		char[] wletters = new char[6]; // stores wrong guesses
		int wrong = 0; // keeps count of incorrect trials
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
			System.out.print("Incorrect guesses: ");
			System.out.println(wletters);
			System.out.print("Puzzle to solve: ");
			System.out.println(answer);
			System.out.print("Please guess a letter: ");
			char guess; // stores guessed letter
			
			// stores user input for guess according to game mode
			if (mode == '0') {
				guess = (char) ((Math.random() * 26) + 65);
			} else {
				guess = s.nextLine().toUpperCase().charAt(0);
			}
			
			found = 0;
			System.out.println(guess);
			
			// checks if guess is in secret word by iterating over all letters and updates answer, solved, and found accordingly
			for (int i = 0; i < letters.length; i++) {
				if (letters[i] == guess) {					
					if (answer[2*i] != guess) {
						answer[2*i] = guess;
						solved++;
					}
					found = 1;
				}
			} 
			
			// hangs if guess is not found in secret word and reprints gallows
			if (found != 1) { 
				
				// adds wrong guess to wrong guesses if not already there
				if (!doesContain(wletters, guess)) {
					wletters[wrong] = guess;
				}
				g.hang();
				System.out.println(g);
				wrong++;
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
