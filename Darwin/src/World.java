/**
 * This module includes the functions necessary to keep track of the creatures
 * in a two-dimensional world. In order for the design to be general, the
 * interface adopts the following design:
 * <p>1. The contents are unspecified objects.
 * <p>2. The dimensions of the world array are specified by the client. <p>
 * There are many ways to implement this structure. HINT:
 * look at the structure.Matrix class. You should need to add no more than 
 * about ten lines of code to this file.
 */

public class World<E> {
	
	Object[][] world;
	int height;
	int width;
	
	/**
	 * This function creates a new world consisting of width 
	 * columns and height rows, each of which is numbered beginning at 0. 
	 * A newly created world contains no objects.
	 * @param w The width of the world that is to be created
	 * @param h The height of the world that is to be created
	 * @pre w > 0
	 * @pre h > 0
	 */
	public World(int h, int w)  {
		world = new Object[h][w];
		height = h;
		width = w;
	}

	/**
	 * Returns the height of the world.
	 */
	public int height() {
		return height;
	}

	/**
	 * Returns the width of the world.
	 */
	public int width() {
		return width;
	}

	/**
	 * Returns whether pos is in the world or not. 
	 * @pre pos is a non-null position. 
	 * @post returns true if pos is an (x,y) location in the bounds of
	 *       the board.
	 */
	boolean inRange(Position pos)  {
		return (pos.getY() >= 0 && pos.getY() < height && pos.getX() >= 0 && pos.getX() < width);
	}

	/**
	 * Sets a position on the board to contain c.
	 * @param c The object that is to be added
	 * @param pos Where c is to be added
	 * @pre pos is a non-null position on the board.
	 */
	public void set(Position pos, E c) {
		world[pos.getY()][pos.getX()] = c;
	}

	/**
	 * Return the contents of a position on the board. 
	 * @pre pos is a non-null position on the board.
	 */
	public E get(Position pos) {
		return (E) world[pos.getY()][pos.getX()];
	}
	
	public static void main(String[] args) {
		World world = new World(10, 10);
		assert world.height() == 10 : "Height error";
		assert world.width() == 10 : "Width error";
		assert world.inRange(new Position(5,5)) : "inRange error";
		assert !world.inRange(new Position(11,11)) : "inRange error";
		Object c = new Object();
		world.set(new Position(5,5), c);
		assert world.get(new Position(5,5)) == c : "set/get error";
	}

}
