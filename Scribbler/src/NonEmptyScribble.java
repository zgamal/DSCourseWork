import java.awt.Color;
import objectdraw.*;

public class NonEmptyScribble implements ScribbleInterface {

	// an edge line of the Scribble
	private Line first;    
	
	// the rest of the Scribble
	private ScribbleInterface rest; 

	public NonEmptyScribble(Line segment, ScribbleInterface theRest) {
		first = segment;
		rest = theRest;
	}
	
	/*
	 * Returns true if the Scribble contains the point.
	 */
	public boolean contains(Location point) {
		return (first.contains(point) || rest.contains(point));
	}
	
	/*
	 * Moves the Scribble by xOffset in the x direction
	 *    and yOffset in the y direction
	 */
	public void move(double xOffset, double yOffset) {
		first.move(xOffset, yOffset);
		rest.move(xOffset, yOffset);
	}
	
	// Sets color of Scribble to specified color.
	public void setColor(Color color) {
		first.setColor(color);
		rest.setColor(color);
	}
	
	// Erases Scribble.
	public void removeFromCanvas() {
		first.removeFromCanvas();
		rest.removeFromCanvas();
	}

}