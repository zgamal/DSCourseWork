/*
 * Definitions of a class that is an extension of WindowController
 * 
 * ZIAD ABDELRAHMAN / PURPLE SECTION
 * 19 MAY 2020
 */

import objectdraw.*;

public class MagnetGame extends WindowController {
	
	// magnets
	private Magnet magnet1;
	private Magnet magnet2;
	
	// stores moving magnet
	private Magnet movingMagnet;
	
	// stores resting magnet
	private Magnet restingMagnet;
	
	// stores last location of cursor
	private Location lpoint;

	public void begin() {
		magnet1 = new Magnet(new Location(100, 100), canvas);
		magnet2 = new Magnet(new Location(250, 200), canvas);
		magnet1.interact(magnet2);
	}
	
    public void onMousePress(Location point) {
    	lpoint = point;
    	if (magnet1.contains(point)) {
    		movingMagnet = magnet1;
    		restingMagnet = magnet2;
    	} else if (magnet2.contains(point)) {
    		movingMagnet = magnet2;
    		restingMagnet = magnet1;
    	}
    }

    public void onMouseDrag(Location point) {   
    	if (movingMagnet.contains(lpoint)) { 
	    	movingMagnet.move(point.getX() - lpoint.getX(), point.getY() - lpoint.getY());
	        movingMagnet.interact(restingMagnet);
	        lpoint = point;
    	}
    }

    public void onMouseClick(Location point) {
    	if (magnet1.contains(point)) {
    		magnet1.flip();
    		magnet1.interact(magnet2);
    	} if (magnet2.contains(point)) {
    		magnet2.flip();
    		magnet2.interact(magnet1);
    	}
    }
    
    public static void main(String[] args) {
    	new MagnetGame().startController(500, 500);
    }

}
