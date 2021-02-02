import java.awt.Color;
import objectdraw.*;

/*
 * The methods supported by all scribble classes.
 */
public interface ScribbleInterface {
	
	// Returns whether point is contained in Scribble.
	public boolean contains(Location point);
	
	// Moves Scribble by dx in x-direction and dy in y-direction.
	public void move(double xOffset, double yOffset);
	
	// Sets color of Scribble to specified color.
	public void setColor(Color color);
	
	// Erases Scribble.
	public void removeFromCanvas();
	
}
