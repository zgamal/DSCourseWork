import java.util.Scanner;
import structure5.*;

public class HumanPlayer implements Player {
	
	private char color;
	
	public HumanPlayer(char color){
		assert(color == HexBoard.WHITE || color == HexBoard.BLACK):("Error: invalid color!");
		this.color = color;
	}
	
	public Player play(GameTree gt, Player opp){
		HexNode cnode = gt.getCurrent();
		Scanner s = new Scanner(System.in); 
		System.out.println(cnode.getBoard().toString());
		if (gt.isDone(cnode)){ 
			return opp;
		} else {
			Vector<HexMove> moves = cnode.getBoard().moves(cnode.getColor()); 
			int i;
			for(i = 0; i < moves.size(); i++){
				System.out.println((i+1) + ". : " + moves.get(i).toString());
			}
			System.out.println((i+1) + ". Quit."); 
			System.out.println("Pick  move!");
			int choice = s.nextInt();
			if(choice == i + 1){ 
				return opp;
			}
			cnode = cnode.getChild(choice - 1);
			gt.setCurrent(cnode);
			return opp.play(cnode.getTree(), this);
		}
	}
	
}