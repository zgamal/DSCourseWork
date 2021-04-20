import structure5.*;
import java.util.Random;

public class RandomPlayer implements Player {
	
	private char color;
	
	public RandomPlayer(char color){
		assert(color == HexBoard.WHITE || color == HexBoard.BLACK):("Color must be white or black");
		this.color = color;
	}
	
	public Player play(GameTree gt, Player opp) {
		Random move = new Random();
		HexNode cnode = gt.getCurrent();
		if (gt.isDone(cnode)){ 
			return opp;
		} else { 
			cnode = cnode.getChild(move.nextInt(cnode.getBoard().moves(cnode.getColor()).size()));
			gt.setCurrent(cnode);
			return opp.play(cnode.getTree(), this);
		}
	}
	
}