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