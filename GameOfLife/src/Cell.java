import java.awt.Color;

public class Cell {

	protected int row; // stores row number
	protected int col; // stores column number
	
	// Cell constructor and initializes instance variables
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	// returns Cell row number
	public int getRow() {
		return row;
	}
	
	// return Cell column number
	public int getCol() {
		return col;
	}
	
	// return true of o is a Cell that equals this cell
	public boolean equals(Object o) {
		if (o instanceof Cell) {
			Cell other = (Cell) o;
			return (row == other.getRow() && col == other.getCol());
		}
		return false;
	}
}

