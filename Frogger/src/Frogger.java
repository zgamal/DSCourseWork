import objectdraw.*;
import java.awt.*;

public class Frogger extends WindowController {

    // Constants defining the sizes of the background components.
    private static final double HIGHWAY_LENGTH = 700;
    private static final double LANE_WIDTH = 100;
    private static final int NUM_LANES = 4;
    private static final double HIGHWAY_WIDTH = LANE_WIDTH * NUM_LANES;
    private static final double LINE_WIDTH = LANE_WIDTH / 10;

    // Constants defining the locations of the background components
    private static final double HIGHWAY_LEFT = 0;
    private static final double HIGHWAY_RIGHT = HIGHWAY_LEFT + HIGHWAY_LENGTH;
    private static final double HIGHWAY_TOP = LANE_WIDTH;
    private static final double HIGHWAY_BOTTOM = HIGHWAY_TOP + HIGHWAY_WIDTH;

    // Constants describing the lines on the highway
    private static final double LINE_SPACING = LINE_WIDTH / 2;
    private static final double DASH_LENGTH = LANE_WIDTH / 3;
    private static final double DASH_SPACING = DASH_LENGTH / 2;
        
    // Constants for describing the frog
    private static final double FROG_WIDTH = 83;
    
    // frog stuff
    private Frog Frog;
    private Location startLocation;
    private Image frogImage;
    
    // lane stuff
    private Lane firstLane;
    private Lane secondLane;
    private Lane thirdLane;
    private Lane fourthLane;
    
    private Location firstLocation;
    private Location secondLocation;
    private Location thirdLocation;
    private Location fourthLocation;
    
    // gets all vehicle images 
	private Image lJeep; 
	private Image rJeep;
	private Image lCar; 
	private Image rCar; 
	private Image lTaxi; 
	private Image rTaxi; 
	private Image lVan; 
	private Image rVan; 

    // This method currently just draws the highway.  
    // You will have to add instructions to create
    // the frog and the Lane ActiveObjects.
    public void begin() {
    	
		// Draw the highway background
		new FilledRect (HIGHWAY_LEFT, 
	                	HIGHWAY_TOP, 
	                	HIGHWAY_LENGTH, 
	                	HIGHWAY_WIDTH, 
	                	canvas);
	
		// Draw the lane dividers
		int whichLine = 1;
		while (whichLine < NUM_LANES) {
		    if (whichLine == NUM_LANES / 2) {
				// The middle line is a no passing line
				drawNoPassingLine (HIGHWAY_TOP + (whichLine * LANE_WIDTH) - (LINE_SPACING / 2 + LINE_WIDTH));
		    } else {
				drawPassingLine (HIGHWAY_TOP + (whichLine * LANE_WIDTH) - (LINE_WIDTH / 2));
		    }
		    whichLine = whichLine + 1;
		}
		resize((int)HIGHWAY_LENGTH, (int) (HIGHWAY_WIDTH + 3 * LANE_WIDTH));
	
	
		// ADD YOUR CODE TO CREATE THE FROG AND THE LANES
		startLocation = new Location(308.5, 525);
    	frogImage = getImage("froggy.gif");
		Frog = new Frog(frogImage, startLocation, LANE_WIDTH, canvas);
		
		// gets all images
		lJeep = getImage("jeep_left.gif");
		rJeep = getImage("jeep_right.gif");
		lCar = getImage("oldcar_left.gif");
		rCar = getImage("oldcar_right.gif");
		lTaxi = getImage("taxi_left.gif");
		rTaxi = getImage("taxi_right.gif");
		lVan = getImage("van_left.gif");
		rVan = getImage("van_right.gif");
		
		// stores images in arrays per direction
		Image[] lVehicles = {lJeep, lCar, lTaxi, lVan};
		Image[] rVehicles = {rJeep, rCar, rTaxi, rVan};
		
		firstLocation = new Location(-50, 420);
		firstLane = new Lane(HIGHWAY_LENGTH, 1, firstLocation, lVehicles, rVehicles, Frog, canvas);
		
		secondLocation = new Location(firstLocation.getX(), firstLocation.getY() - 100);
		secondLane = new Lane(HIGHWAY_LENGTH, 1, secondLocation, lVehicles, rVehicles, Frog, canvas);
		
		thirdLocation = new Location(secondLocation.getX() + 700, secondLocation.getY() - 100);
		thirdLane = new Lane(HIGHWAY_LENGTH, -1, thirdLocation, lVehicles, rVehicles, Frog, canvas);
		
		fourthLocation = new Location(thirdLocation.getX(), thirdLocation.getY() - 100);
		fourthLane = new Lane(HIGHWAY_LENGTH, -1, fourthLocation, lVehicles, rVehicles, Frog, canvas);
		
	}
	
	// Draws a pair of solid yellow lines to represent a no passing 
	// divider between lanes
	// Parameter:  y - the top of the top line
	//
	// YOU SHOULD NOT NEED TO MODIFY THIS METHOD
	//
    public void drawNoPassingLine (double y) {
	    	
		// Draw the solid dividing lines
		FilledRect topLine = new FilledRect (HIGHWAY_LEFT, y, HIGHWAY_LENGTH, LINE_WIDTH, canvas);
		topLine.setColor (Color.yellow);
		
		FilledRect bottomLine = new FilledRect (HIGHWAY_LEFT, y + LINE_WIDTH + LINE_SPACING,HIGHWAY_LENGTH, LINE_WIDTH, canvas);
		bottomLine.setColor (Color.yellow);
	}
	
	// Draws a dashed white line to represent a passing line dividing two 
	// lanes of traffic
	// Parameters:  y - the top of the line.
	//
	// YOU SHOULD NOT NEED TO MODIFY THIS METHOD
	//
	public void drawPassingLine (double y) {
	    	
		double x = HIGHWAY_LEFT;
		FilledRect dash;
		
		while (x < HIGHWAY_RIGHT) {
			// Draw the next dash.
			dash = new FilledRect (x, y, DASH_LENGTH, LINE_WIDTH, canvas);
			dash.setColor (Color.white);
			x = x + DASH_LENGTH + DASH_SPACING;
		}
    }

    // Moves Frog around or reincarnates it
    public void onMousePress(Location point) {
    	
    	if (Frog.isAlive()) {
    		Frog.hopToward(point);
    	} else if (point.getY() > 400.0D) {
    		Frog.reincarnate();
    	}
    }
    
    // Main Method starts controller
	public static void main(String[] args) {
		
    	(new Frogger()).startController(500, 500);
    }

}
