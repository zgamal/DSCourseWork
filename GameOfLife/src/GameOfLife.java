/*
 * THOUGHT QUESTIONS
 * 
 * 1. Pressing the key g leads eventually to a configuration of cells with limitless number of cells. The glider gun emits cells at a
 *    constant rate and infitely. This is very interesting because it proves that in cellular automtata it is possible to have 
 *    configurations with limitless numbers of cells from an intital pattern with finite numbers of cells. 
 *    
 * 3. One of the cons is that this will likely increase the amount of code you have to write, since the array grid will have to be initalized/
 *    constructed again in the grid class in order for its functionality to work properly. A possible pro is that it guarentees that the grid/
 *    borders 2D arrsu are initialized and defined and ready to use.
 * 
 * 2. The cells change concurrently which means that the next generation version of each cell is calculated and updates for all cells
 *    at the same time. Since the array that holds the status of all those cells is predetermined and its size is predetrmined based on 
 *    the board's size, the amount of memory that we need to play the game is fixed throughout. 
 *    
 * 4. I would start with a fixed size version of the board and then as the board expands, and new cells are born beyond the initial dimensions
 *    of the board, new rows/columns of cells are created to host those new cells.
 * 
 */

import java.awt.Color;
import java.awt.Container;
import javax.swing.JRootPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import objectdraw.*;
public class GameOfLife extends WindowController implements KeyListener {
	
	protected static final int WINDOW_SIZE = 616;
	protected static final int BOX_SIZE = 15;
	protected Cell lastToggledCell;
	
	protected Grid grid;
	
	public void begin() {
		int yoffset = 0;
		
		/* The coordinate system of the grid is thrown off slightly by
		 * the existance of the system menu bar.  The code below figures out
		 * the hight of the menu bar. The call to resize at the end of this
		 * method takes this offset into account when making the whole grid
		 * visible. 
		 */
		Container c = this;
		while(! (c instanceof JRootPane)) {
			yoffset += (int)(c.getParent().getY());
			c = c.getParent();
		}
		grid = new Grid(WINDOW_SIZE, BOX_SIZE, canvas);
        requestFocus();
        addKeyListener(this);
        canvas.addKeyListener(this);
        lastToggledCell = null;
        resize(WINDOW_SIZE, WINDOW_SIZE + yoffset);
	}
	
	// Toggles the cell that was clicked on and keep track of it
	public void onMousePress(Location point) {
		lastToggledCell = grid.toggle(point);
	}
	
	// Toggle the cell under the mouse if it wasn't the last cell to be toggled
	public void onMouseDrag(Location point) {
		
	    if (grid.getCell(point) != null && !grid.getCell(point).equals(lastToggledCell)) {
	      lastToggledCell = grid.toggle(point); 
	    }
	}
	
    // Required by KeyListener Interface.
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    
    // If letter is g, calls gliderGun, else if letter is c, clears, else toggles
    public void keyTyped(KeyEvent e) {
    	char letter = e.getKeyChar();
    	if (letter == 'g' && lastToggledCell != null) {
    		grid.gliderGun(lastToggledCell.getRow(), lastToggledCell.getCol());
    	} else if (letter == 'c') {
    		
    		// Clears grid
    		grid.clear();
    	} else {
    		
    		// Toggles running
    		grid.toggleRunning();
    	}
    }
	
    public static void main(String[] args) { 
        new GameOfLife().startController(WINDOW_SIZE, WINDOW_SIZE); 
	}

}
