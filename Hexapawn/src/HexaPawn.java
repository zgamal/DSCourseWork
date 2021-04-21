/* THOUGHT QUESTIONS 
 * 
 * 1. For a 3x4 board, there are 6003 positions, and for a 3x5 board, there are 215150 positions. Those numbers can be determined using a static int to
 *    be incremented when a new GameTree (and board) is constructed.
 * 
 * 2. Based on testing, I think that H.E.R will ultimately win more frequently. This could  suggest that H.I.M kept trimming its trees as expected but 
 *	  was unable to catch up with H.E.R, which, by moving second, has a greater chance of learning the perfect way to win. By moving second, H.E.R is 
 *    able to force itself on H.I.M and dictate the flow of the game early on, where the board state becomes completely dependent on its movements. 
 *    Thus, H.E.R can rapidly figure out the best and quickest path down the tree that would guarantee a win. As shown by the tests, after a few rounds H.E.R 
 *    learns the best paths for each H.I.M move and begins to dominate over it. However, for large boards, H.I.M’s chances improve. In the 3x4 board, 
 *    H.I.M won about 800/1000, but, on the 4x3 board, the relationship swapped again and HER won about 900/1000. When increasing the dimensions to 3x5
 *    and 5x3, the same relationship is seen. This could be due to the fact that the larger the board dimensions, the longer it takes to find the 
 *    optimum strategy.  
 * 
 * 3. The most efficient way to implement this would be that when the board is symmetric (3x3 board where left and right columns are identical), store
 *    only one set of the children’s moves and add a boolean instance in the GameTree class called mirror that would keep track if a certain board is a
 *    mirror of another. If it is, then using another method, that GameTree node would reference the mirrored one, such that when it calls any
 *    function, the code for the mirror can be reused on the current reflection of the node. And so, we only need to have on GameTree object that would
 *    be a reference to the other one when mirror is true. Finally, we would need a method to print the mirrored version when mirror is true by
 *    iterating through and swapping the positions as necessary to their mirrored correct one.
 * 
 */


import structure5.*;
import java.util.Scanner;

public class HexaPawn {
	
	private static int p1score = 0;
	private static int p2score = 0;
	
	public static void main(String [] args) {
		System.out.println("Choose player 1 (type 1 letter): random (r), human (h), computer (c)");
		Scanner s = new Scanner(System.in);
		String p1str = s.next();
		System.out.println("Choose player 2 (type 1 letter): random (r), human (h), computer (c)");
		String p2str = s.next();
		Player p1;
		Player p2;
		
		// Sets up board
		HexBoard gb = new HexBoard(3, 3);
		GameTree gt = new GameTree(gb, HexBoard.WHITE);
		final HexNode root = gt.getCurrent();
		
		// Sets up player 1
		if(p1str.equals("h")){
			p1 = new HumanPlayer(HexBoard.WHITE);
		} else if(p1str.equals("c")){
			p1 = new CompPlayer(HexBoard.WHITE);
		} else { //final option is random bottisimmus
			p1 = new RandomPlayer(HexBoard.WHITE);
		}
		
		// Sets up player 2 
		if(p2str.equals("h")){
			p2 = new HumanPlayer(HexBoard.BLACK);
		} else if(p2str.equals("c")){
			p2 = new CompPlayer(HexBoard.BLACK);
		} else { //bot
			p2 = new RandomPlayer(HexBoard.BLACK);
		}
		
		// Stimulates game 
		if(!p1str.equals("h") && !p2str.equals("h")){
			simulateGame(gt, root, p1, p2);
		} else {
			play(gt, root, p1, p2);
		}
		
	}
	
	// Stimulates 1000 games
	public static void simulateGame(GameTree gameTree, HexNode root, Player p1str, Player p2str){
		for(int i = 0; i < 1000; i++){
			Player winner = p1str.play(gameTree, p2str);
			if(winner == p1str){
				p1score++;
			} else {
				p2score++;
			}
			gameTree.setCurrent(root);
		}
		System.out.println("Player 1 wins: " + p1score);
		System.out.println("Player 2 wins: " + p2score);
	}
	
	// Plays game
	public static void play(GameTree gameTree, HexNode root, Player p1, Player p2){
		Scanner in = new Scanner(System.in);
		Player winner = p1.play(gameTree, p2);
		if(winner == p1){
			System.out.println("Player 1 has won!");
		} else {
			System.out.println("Player 2 has won!");
		}
		System.out.println("Number of nodes: " + gameTree.getCount());
		System.out.println("Game over! Do you want to play another round? (yes / no)");
		String response = in.next();
		if (response.equals("no")) {
			return;
		} else {
			gameTree.setCurrent(root);
			play(gameTree, root, p1, p2);
		}
	}
	
}