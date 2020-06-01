import objectdraw.*;
import java.awt.*;

public class Lane extends ActiveObject {

   // Distance from front bumper to back bumper of the longest vehicle, in pixels.
   private static final int MAX_VEHICLE_SIZE = 139;
   
   // vehicle distance
   private double vehicleDistance;
    
   // vehicle velocity
   private double vehicleVelocity;
   
   // vehicle location
   private Location vehicleLocation;
       
   // vehicle image
   private Image vehicleImage;
   
   // frog
   private Frog Frog;
   
   // vehicle canvas
   private DrawingCanvas Canvas;
   
   // stores vehicle images per direction
   private Image[] lVehicles;
   private Image[] rVehicles;
   
   // lane direction
   private int laneDirection;
   
   // random vehicle generator
   private RandomIntGenerator indexGenerator;
   private int index;

   // Constructor for lane
   public Lane(double distance, int direction, Location location, Image[] left, Image[] right, Frog frog, DrawingCanvas canvas) {
          
    	vehicleDistance = distance;
    	vehicleVelocity = direction * (100.0D * Math.random() + 33.0D) / 1000.0D;
    	vehicleLocation = location;
    	lVehicles = left;
    	rVehicles = right;
    	Frog = frog;
    	laneDirection = direction;
    	Canvas = canvas;
        start();
    }

   // Producing vehicles
   public void run() {
	   
        while (true) {
        	indexGenerator = new RandomIntGenerator(0,3);
        	if (laneDirection == 1) {
        		index = indexGenerator.nextValue();
            	Vehicle Vehicle = new Vehicle(rVehicles[index], vehicleLocation, vehicleVelocity, vehicleDistance, Frog, Canvas);
            	pause(Math.abs((2.0 + Math.random() * 2.0) * MAX_VEHICLE_SIZE / vehicleVelocity));
        	} else {
        		index = indexGenerator.nextValue();
            	Vehicle Vehicle = new Vehicle(lVehicles[index], vehicleLocation, vehicleVelocity, vehicleDistance, Frog, Canvas);
            	pause(Math.abs((2.0 + Math.random() * 2.0) * MAX_VEHICLE_SIZE / vehicleVelocity));
        	}
        }
    }
    
}
