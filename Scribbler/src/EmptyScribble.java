import java.awt.Color;
import objectdraw.*;

/*
 * Class representing an empty scribble.
 */

public class EmptyScribble implements ScribbleInterface {
	
	public EmptyScribble() {
		
	}
	
	// Point is never in an empty scribble!
	public boolean contains(Location point) {
		return false;
	}
	
	// Nothing to move, so do nothing!
	public void move(double xOffset, double yOffset) {
	}
	
	// Nothing to color, so do nothing!
	public void setColor(Color color) {
	}
	
	// Nothing to erase, so do nothing!
	public void removeFromCanvas() {
	}
	
}