import objectdraw.*;
import java.awt.*;

public class Vehicle extends ActiveObject {
	
	// vehicle image
	private VisibleImage vehicleImage;

	// frog
	private Frog Frog;

	// vehicle velocity
	private double velocityVehicle;	
	
	// vehicle travel distance
	private double endDistance;
	
	// vehicle distance traveled
	private double lanePosition;

	
	
    public Vehicle(Image image, Location start, double velocity, double distance, Frog frog, DrawingCanvas canvas) {
    	
    	vehicleImage = new VisibleImage(image, start, canvas);
    	Frog = frog;
    	velocityVehicle = velocity;
    	endDistance = distance;
    	lanePosition = 0;
        start();
    }


    public void run() {
    	
    	while (lanePosition < endDistance) {
	    	double currentTime = System.currentTimeMillis();
	    	pause(30);
	    	double pauseTime = System.currentTimeMillis() - currentTime;
	    	double pauseDistance  = velocityVehicle * pauseTime;
	    	vehicleImage.move(pauseDistance, 0);
	    	lanePosition += Math.abs(pauseDistance);
	    	if (Frog.overlaps(vehicleImage)) {
	    		Frog.kill();
	    	}
    	} 
    	vehicleImage.removeFromCanvas();
    }
    
}
