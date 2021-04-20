import structure5.*;
import java.util.Random;
import java.util.Iterator;
import java.util.Scanner;

public class HexBoard {
	
	// Constants for Hexboard
	public final static char WHITE = 'o';
	public final static char BLACK = '*';
	public final static char SPACE = ' ';
	
	// Variables for Hexboard
	private int board[];
	private int r, c;
	private Vector<HexMove> wmoves, bmoves;

	public HexBoard() { 
		this(3,3);
	}
	
	public HexBoard(int r, int c)  {
		this.r = r;
		this.c = c;
		board = new int[r*c];
		for (int pos = 0; pos < r*c; pos++) {
			if (pos < c) board[pos] = WHITE;
			else if (pos >= (r-1)*c) board[pos] = BLACK;
			else board[pos] = SPACE;
		}
		wmoves = bmoves = null;
	}
	
	public HexBoard(HexBoard orig, HexMove m) {
		r = orig.r;
		c = orig.c;
		wmoves = bmoves = null;
		board = new int[r*c];
		for (int i = 0; i < r*c; i++) board[i] = orig.board[i];
		board[m.to()] = board[m.from()];
		board[m.from()] = SPACE;
	}
	
	public static char opponent(char m) {
		return (m == WHITE)? BLACK: WHITE;    
	}
	
	public boolean win(char m)	{	
		if (m == WHITE) {
			for (int pos = (r-1)*c; pos < r*c; pos++)
			{
				if (board[pos] == WHITE) return true;
			}
			return 0 == moves(BLACK).size();
		} else {
			for (int pos = 0; pos < c; pos++)
			{
				if (board[pos] == BLACK) return true;
			}
			return 0 == moves(WHITE).size();
		}
	}
	
	public Vector<HexMove> moves(char m) {
		if (m == WHITE) {
			if (wmoves != null) return wmoves;
			wmoves = new Vector<HexMove>();
			for (int pos = 0; pos < (r-1)*c; pos++) {
				if (board[pos] == WHITE) {
					if (board[pos+c] == SPACE) {
						wmoves.addElement(new HexMove(pos,pos+c,c));
					}
					if ((pos%c)!=0 && board[pos+(c-1)] == BLACK) {
						wmoves.addElement(new HexMove(pos,pos+c-1,c));
					}
					if ((pos%c)!=(c-1) &&
							board[pos+c+1] == BLACK)
					{
						wmoves.addElement(new HexMove(pos,pos+c+1,c));
					}
				}
			}
			return wmoves;
		} else {
			if (bmoves != null) return bmoves;
			bmoves = new Vector<HexMove>();
			for (int pos = c; pos < r*c; pos++)
			{
				if (board[pos] == BLACK)
				{
					if (board[pos-c] == SPACE)
					{
						bmoves.addElement(new HexMove(pos,pos-c,c));
					}
					if ((pos%c)!=0 &&
							board[pos-c-1] == WHITE)
					{
						bmoves.addElement(new HexMove(pos,pos-c-1,c));
					}
					if ((pos%c)!=c-1 &&
							board[pos-c+1] == WHITE)
					{
						bmoves.addElement(new HexMove(pos,pos-c+1,c));
					}
				}
			}
			return bmoves;
		}
	}
	
	public String toString() {
		String result = "-";
		for (int pos = 0; pos < c; pos++) result += "--";
		result += "\n";
		for (int pos = 0; pos < r*c; pos++)
		{
			result += " "+((char)board[pos]);
			if ((c-1) == (pos%c)) result += '\n';
		}
		for (int pos = 0; pos < c; pos++) result += "--";
		result += "-\n\n";
		return result;
	}
	
	// For running tests
	public static void main(String[] args) { 
		HexBoard board = new HexBoard(3,3); 
		Vector<HexMove> moves;
		Scanner in = new Scanner(System.in);
		int amove;
		int bmove;
		Random gen = new Random();
		System.out.println(board);
		do {
			moves = board.moves(WHITE);
			Iterator it = moves.iterator();
			int j = 0;
			while (it.hasNext())  {
				System.out.println(j + ". " + it.next());
				j++;
			}
			bmove = in.nextInt();
			board = new HexBoard(board,moves.elementAt(bmove)); 
			System.out.println(board);
			if (board.win(WHITE)) { System.out.println("Game won!"); break; } 
			moves = board.moves(BLACK); 
			amove = gen.nextInt(moves.size());
			board = new HexBoard(board,moves.elementAt(amove));
			System.out.println("I "+ moves.elementAt(amove));
			System.out.println(board);
			if (board.win(BLACK)) { 
				System.out.println("Game won!"); 
				break; 
			}
		} 
		while (true); 
	}

}