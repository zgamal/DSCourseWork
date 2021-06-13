import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class TowersOfHanoiCLI {
	public static String getVictoryMessage(int disks, int posts, int moves) {
		
		// the least number of moves possible to win the game per number of disks (for a 3-post game)
		double optimal_score = Math.pow(2, disks) - 1; 
		
		String message = "You won!"; 
		
		// if a 3-post game, customize victory message by optimum score scored by the user
		if (posts == 3) {
			if (moves == optimal_score) {
				message = "You won with the best possible score!";
			} else if (moves > optimal_score && moves  <= optimal_score*1.2) {
				message = "You won with the best possible score!";;
			}
		}
		
		// returns custom-made victory message
		return message; 
	}
	
	public static void main(String[] args) { 
		

		
		// stores number of moves done by user
		int moves = 0; 	
		
		// true if the game is running and has not been won, false otherwise
		boolean running = true; 
		
		// manages user interaction to get number of disks and posts for the game
		Scanner s = new Scanner(System.in);
		System.out.println("Insert Number of Disks (3-7): ");
		int numdisks = s.nextInt();
		System.out.println("Insert Number of Posts (3-5): ");
		int numposts = s.nextInt();
		
		// ArrayList of stacks of type integer that represents the posts and the disks on them
		ArrayList<Stack<Integer>> stacks = new ArrayList<Stack<Integer>>(); 
		
		// builds the posts using the parameters given by the user
		for (int i = 0; i < numposts; i++) {
			stacks.add(new Stack<Integer>()); // adds the posts
		}
		if (numdisks <= 7 && numdisks >= 3){
			for (int i = numdisks; i > 0; i--) {
				stacks.get(0).push(i); // adds the disks
			}
		}
		
		// plays the game until user wins
		while (running) {
			
			// prints the current status of each post
			for (int i = 1; i <= numposts; i++) {
				System.out.println("Post " + i + ": " + stacks.get(i-1));
			}
			
			// manages user interaction to move a disk from one post to another
			System.out.println("Move top disk from post number ");
			int from = s.nextInt() - 1;
			System.out.println("to Post number ");
			int to = s.nextInt() - 1;
			
			// if user's input is valid, a disk is moved from one post to another per the input and moves is incremented
			if (!stacks.get(from).empty() && (stacks.get(to).empty() || stacks.get(to).peek() > stacks.get(from).peek())) {
				stacks.get(to).add(stacks.get(from).pop());
				moves++;
			} 
			
			// checks if game is won, stops running and prints victory message if it is
			if(stacks.get(stacks.size()-1).size() == numdisks) {
				running = false;
				System.out.println(getVictoryMessage(numdisks, stacks.size(), moves));
			}
		}
	}
}



