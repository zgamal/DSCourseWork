import java.awt.Color;
import objectdraw.*;

public class Grid extends ActiveObject {

	protected FilledRect[][] grid;
	protected FramedRect[][] borders;
	protected int box_size;
	protected boolean running;
	protected static final int XOFFSET = 8;
	protected static final int YOFFSET = 8;
	
	// Constructor for Grid
	public Grid (int window_size, int box_size, DrawingCanvas canvas) {
		this.box_size = box_size;
		grid = new FilledRect[(window_size - 2 * YOFFSET) / box_size][(window_size - 2 * XOFFSET) / box_size];
		borders = new FramedRect[(window_size - 2 * YOFFSET) / box_size][(window_size - 2 * XOFFSET) / box_size];
		for(int row = 0; row < grid.length; row++) {
			for(int col = 0; col < grid[0].length; col++) {
				grid[row][col] = new FilledRect(col * box_size + XOFFSET, row * box_size + YOFFSET, box_size, box_size, canvas);
				grid[row][col].setColor(Color.WHITE);
				borders[row][col] = new FramedRect(col * box_size + XOFFSET, row * box_size + YOFFSET, box_size, box_size, canvas);
			}
		}
		running = false;
    	start();
	}
	
	// Returns the cell in which the given point resides. 
	public Cell getCell(Location point) {
	    if (point.getX() >= XOFFSET && point.getX() <= (box_size * grid[0].length) + XOFFSET 
	    		&& point.getY() >= YOFFSET && point.getY() <= (box_size * grid.length) + YOFFSET) {
	    	return new Cell((int) ((point.getY() - YOFFSET) / box_size), (int) ((point.getX() - XOFFSET) / box_size));
	    } else {
	    	return null;
	    }
	}
	
	// Makes a black cell white or a white cell black. Also returns the toggled cell. 
	public Cell toggle(Location point) {
		Cell c = getCell(point);
		toggle(c.getRow(), c.getCol());
		return c;
	}
	
	// Given a row and column in the grid, switches the color of the cell at that position. 
	public void toggle(int row, int col) {
		if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length) {
			if (grid[row][col].getColor() == Color.WHITE) {
				grid[row][col].setColor(Color.BLACK);
			} else {
				grid[row][col].setColor(Color.WHITE);
			}
		}
	}
	
	public void toggleRunning() {
		running = !running;
	}
	
	// Returns true if the cell at the given row and col is alive.
	protected boolean isAlive(int row, int col) {
		if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length) {
			return grid[row][col].getColor() == Color.BLACK;
		} else {
			return false;
		}
	}
	
	// Returns the number of alive cells that are adjacent to the given row and col. 
	protected int liveNeighbors(int row, int col) {
	    int neighbors = 0;
	    Cell[] ncells = {new Cell(row - 1, col - 1), new Cell(row - 1, col), new Cell(row - 1, col + 1),
	    		new Cell(row, col - 1), new Cell(row, col + 1),
	    		new Cell (row + 1, col - 1), new Cell(row + 1, col), new Cell(row + 1, col + 1)};
	    for (int i = 0; i < ncells.length; i++) {
	    	if (isAlive(ncells[i].getRow(), ncells[i].getCol())) {
	    		neighbors++;
	    	}
	    }
	    return neighbors;
	}
	
	// Sets all of the cells in the grid to WHITE/off/dead
	public void clear() {
		for (int row = 0; row < grid.length; row++) {
			for(int col = 0; col < grid[0].length; col++) {
				grid[row][col].setColor(Color.WHITE);
			}
		}
	}
	
	// Sets a given cell to BLACK/alive/on if it is within the grid. 
	private void on(int row, int col) {
		if ((row >= 0 && row < grid.length) && (col >= 0 && col < grid[0].length)) {
			grid[row][col].setColor(Color.BLACK);
		}
	}
	
	// Mystery method. Figure out when it gets used and why it's interesting.  
	public void gliderGun(int row, int col) {
		on(row,col);
		on(row,col+1);
		on(row+1,col);
		on(row+1,col+1);
		on(row,col+10);
		on(row+1,col+10);
		on(row+2,col+10);
		on(row+3,col+11);
		on(row-1,col+11);
		on(row-2,col+12);
		on(row-2,col+13);
		on(row+4,col+12);
		on(row+4,col+13);
		on(row+1,col+14);
		on(row-1,col+15);
		on(row+3,col+15);
		on(row,col+16);
		on(row+1,col+16);
		on(row+2,col+16);
		on(row+1,col+17);
		on(row,col+20);
		on(row,col+21);
		on(row-1,col+20);
		on(row-1,col+21);
		on(row-2,col+20);
		on(row-2,col+21);
		on(row-3,col+22);
		on(row+1,col+22);
		on(row-3,col+24);
		on(row+1,col+24);
		on(row-4,col+24);
		on(row+2,col+24);
		on(row-1,col+34);
		on(row-2,col+34);
		on(row-1,col+35);
		on(row-2,col+35);
	}
	
	// Logic to play GameOfLife
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
}
