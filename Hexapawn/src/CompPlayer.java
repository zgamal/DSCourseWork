import structure5.*;
import java.util.Random;

public class CompPlayer implements Player {
	
	private boolean trim = false;
	private char color;
	
	public CompPlayer(char color){
		assert(color == HexBoard.WHITE || color == HexBoard.BLACK): ("Invalid color.");
		this.color = color;
	}
	
	// Plays randomly and trims losing moves
	public Player play(GameTree gt, Player opp){
		HexNode cnode = gt.getCurrent();
		Random move = new Random();
		
		if (gt.isDone(cnode)){ 
			trim = true; 
			return opp;
		} else { 
			HexNode nnode = cnode.getChild(move.nextInt(cnode.childrenSize()));
			gt.setCurrent(nnode);
			Player win = opp.play(nnode.getTree(), this);
			if (win == opp && trim == true){
				cnode.removeChild(nnode); 
				trim = false; 
			}
			return win; 
		}
	}
	
}