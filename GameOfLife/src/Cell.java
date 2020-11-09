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

/*
public void run() {
while(true) {
	if (running) {
		boolean [][] ngen = new boolean[grid.length][grid[0].length];
		for (int row = 0; row < grid.length; row++) {
			for(int col = 0; col < grid[0].length; col++) {
				int neighbors = liveNeighbors(row, col);
				if (isAlive(row, col)) {
					if (neighbors == 2 || neighbors == 3) {
						ngen[row][col] = true;
					} else {
						ngen[row][col] = false;
					}
				} else {
					if (neighbors == 3) {
						ngen[row][col] = true;
					} else {
						ngen[row][col] = false;
					}
				}
			}
		}
		for (int row = 0; row < ngen.length; row++) {
			for(int col = 0; col < ngen[0].length; col++) {
				if (ngen[row][col]) {
					grid[row][col].setColor(Color.BLACK);
				} else {
					grid[row][col].setColor(Color.WHITE);
				}
			}
		}
	}
	pause(100);	
} 
}
*/