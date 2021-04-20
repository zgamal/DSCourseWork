import structure5.*;
import java.util.Iterator;

public class HexNode {
	
	Vector<HexNode> children;
	HexBoard board;
	GameTree gt;
	char color;

	public HexNode(HexBoard board, char color, GameTree gt){
		children = new Vector<HexNode>();
		this.board = board;
		this.gt = gt;
		this.color = color;
	}

	public HexNode getChild(int numberChoice){
		assert(numberChoice >= 0): ("Error: invalid!");
		return children.get(numberChoice);
	}
	
	public int childrenSize(){
		return children.size();
	}
	
	public GameTree getTree(){
		return gt;
	}
	
	public char getColor(){
		return color;
	}
	
	public void addChild(HexNode nboard){
		assert(nboard != null):("New node cannot be null");
		children.add(nboard);
		gt.setCount(gt.getCount()+1);
	}
	
	public HexBoard getBoard(){
		return board;
	}
	
	public void removeChild(HexNode board){
		assert(board != null):("Game board cannot be null");
		children.remove(board);
		gt.setCount(gt.getCount()-1);
	}

}