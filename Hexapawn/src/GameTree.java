import structure5.*;

class GameTree {
	
	private HexNode r;
	private HexNode cnode;
	private int count = 1;
	
	public GameTree() {
		this(new HexBoard(), HexBoard.WHITE);
	}

	public GameTree(HexBoard cboard, char color) {
		assert(cboard != null): ("Error: Board does not exist!");
		r = new HexNode(cboard, color, this);
		cnode = r;
		build(r, color);
	}
	
	// Helper method for constructor that buid the trees
	public void build(HexNode cnode, char color) {
		assert(cnode != null):("Error: Board does not exist!");
		Vector<HexMove> moves = cnode.getBoard().moves(color);
		HexBoard cboard = cnode.getBoard();
		char ccolor = cnode.getColor(); 
		if (moves.size() == 0 || cboard.win(cboard.opponent(ccolor)) ) {
			return;
		} else { 
			for(int i = 0; i < moves.size(); i++){
				HexNode nnode = new HexNode(new HexBoard(cnode.getBoard(), moves.get(i)), cnode.getBoard().opponent(color), this);
				cnode.addChild(nnode);
				build(nnode, cnode.getBoard().opponent(color));
			}
		}
	}
	
	// Returns count
	public int getCount() {
		return count;
	}
	
	// Updates count
	public void setCount(int count) {
		assert(count >= 0):("The count has to be positive");
		this.count = count;
	}
	
	// Gets current node
	public HexNode getCurrent() {
		return cnode;
	}
	
	// Updates current node
	public void setCurrent(HexNode nnode) {
		assert(nnode != null):("Error: Node does not exist!");
		this.cnode = nnode;
	}
	
	// Returns true if the game is done or there are no possible moves left
	public boolean isDone(HexNode nnode) {
		assert(nnode != null): ("Error: Node does not exist!");
		HexBoard cboard = cnode.getBoard();
		char ccolor = cnode.getColor();
		return cboard.win(cboard.opponent(ccolor)) || cnode.childrenSize() == 0;
	}
	
}