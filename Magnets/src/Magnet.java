/*
 * Definition of class of objects used to represent bar magnets.
 * 
 * ZIAD ABDELRAHMAN / PURPLE SECTION
 * 19 MAY 2020
 */

import objectdraw.*;

public class Magnet {

    //  dimensions of magnets	
    private static final double MAGNET_WIDTH = 150;
    private static final double MAGNET_HEIGHT = 50;
    
    // fixed distance for poles
    private static final double POLE_DISTANCE = (MAGNET_HEIGHT /2);

    //  box representing perimeter of magnet
    private FramedRect box;
    
    // poles for magnet
    private Pole nPole;
    private Pole sPole;
    
    //  Create a new magnet at location upperLeft and two poles at correct positions
    public Magnet(Location upperLeft, DrawingCanvas canvas) {
    	box = new FramedRect(upperLeft, MAGNET_WIDTH, MAGNET_HEIGHT, canvas);
    	nPole = new Pole(this, upperLeft.getX() + POLE_DISTANCE, upperLeft.getY() + POLE_DISTANCE, "N", canvas);
    	sPole = new Pole(this, upperLeft.getX() + MAGNET_WIDTH - POLE_DISTANCE, upperLeft.getY() + POLE_DISTANCE, "S", canvas);
    }

    // Moves magnet and two poles by specified amount
    public void move(double xoff, double yoff) {
    	box.move(xoff, yoff);
    	nPole.move(xoff, yoff);
    	sPole.move(xoff, yoff);
    }

    // Moves magnet and two poles to specified location
    public void moveTo(Location point) {
    	double xDistance = point.getX() - box.getX();
    	double yDistance = point.getY() - box.getY();
    	
    	box.move(xDistance, yDistance);
    	nPole.move(xDistance, yDistance);
    	sPole.move(xDistance, yDistance);
    }
    
    // Returns the upper-left coordinates of the magnet
    public Location getLocation() {
        return box.getLocation();
    }
    
    // Returns true if magnet contains specified point
    public boolean contains(Location point) {
        if (box.contains(point)) {
        	return true;
        } else {
        	return false;
        }
    }

    // Returns the width of the magnet
    public double getWidth() {
        return MAGNET_WIDTH;
    }

    // Returns the height of the magnet
    public double getHeight() {
        return MAGNET_HEIGHT;
    }

    // Reverses north and south poles
    public void flip() {
    	double xNpole = nPole.getX();
    	double xSpole = sPole.getX();
    	
    	nPole.move(xSpole - xNpole, 0);
    	sPole.move(xNpole - xSpole, 0);
    }

    // Returns north pole
    public Pole getNorth() {
    	return nPole;
    }
    
    // Returns south pole
    public Pole getSouth() {
    	return sPole;
    }
    
    // Interacts movingMagnet with restingMagnet
    public void interact(Magnet restingMagnet) {
    	nPole.attract(restingMagnet.getSouth());
    	sPole.attract(restingMagnet.getNorth());
    	nPole.repel(restingMagnet.getNorth());
    	sPole.repel(restingMagnet.getSouth());
    }
    
}