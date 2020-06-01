import objectdraw.*;
import java.awt.*;

public class Frog {
	
    // frog image height (unused)
   // private static final double FROG_HEIGHT = 48;
    
    // frog image
    private VisibleImage frogImage;
    
    // frog hopping distance
    private double hopDistance;
    
    // frog status
    private boolean isAlive;
    
    // frog starting location
    private Location startLocation;
    
    // frog death message 
    private Text deathMessage;
    
    // Constructor for the Frog
    public Frog(Image image, Location start, double width, DrawingCanvas canvas) {
    	
    	frogImage = new VisibleImage(image, start, canvas);
    	hopDistance = width;
    	isAlive = true;
    	startLocation = start;
    	deathMessage = new Text("OUCH!", start, canvas);
    	deathMessage.setColor(Color.RED);
    	deathMessage.hide();
    }

    // Hopping of the Frog (to do)
    public void hopToward(Location point) {
    	
    	if (isAlive) {
    		if (point.getX() < frogImage.getX()) {
    			frogImage.move(-1 * hopDistance, 0);
    		} else if (point.getX() > (frogImage.getX() + frogImage.getWidth())){
    			frogImage.move(hopDistance, 0);
    		} else if (point.getY() < frogImage.getY()) {
    			frogImage.move(0, -1 * hopDistance);
    		} else {
    			frogImage.move(0, hopDistance);
    		}
    	}
    }
    
    // Returns true if Frog image overlaps Vehicle image
    public  boolean overlaps(VisibleImage vehicleImage) {
    	
    	return frogImage.overlaps((Drawable2DInterface) vehicleImage);
    }

    // Kills the frog and shows death message
    public void kill () {
    	
    	isAlive = false;
    	deathMessage.show();
    }

    // Reincarnates Frog 
    public void reincarnate() {
    	
    	if (!isAlive) {
    		deathMessage.hide();
    		frogImage.moveTo(startLocation);
    		isAlive = true;
    	}
    }

    // Return true if Frog is alive
    public boolean isAlive () {
    	
    	return isAlive;
    }

}
