import java.awt.Color;
import java.awt.Image;
import java.util.Vector;

import objectdraw.*;

public class Mothership extends ActiveObject {

	// Costants for Mothership
	private static final int SHIP_WIDTH = 50;
	private static final int SHIP_HEIGHT = 50;
	private static final int BULLET_VELOCITY = -40;
	private static final int BULLET_GAP = 11;
			
	// Istance variables for Mothership
	private VisibleImage mothership;
	private SpaceInvaders main;
	private DrawingCanvas canvas;
	private Invaders invaders;
	private Bullet bullet;
	private Vector<Bullet> bullets;
	private boolean isAlive;
	private int direction;
	
	// Costructs Mothership with parameters given
	public Mothership(Image img, SpaceInvaders main, Invaders invaders, DrawingCanvas canvas) {
		mothership = new VisibleImage(img, canvas.getWidth()/2, canvas.getHeight()-75, SHIP_WIDTH, SHIP_HEIGHT, canvas);
		this.main = main;
		this.invaders = invaders;
		this.canvas = canvas;
		bullets = new Vector<Bullet>();
		isAlive = true;
		direction = 0;
		start();
	}
	
	// Returns location of Mothership in x-direction
	public double getX() {
		return mothership.getX();
	}
	
	// Sets direction of Mothership to amount given
	public void setDirection (int d) {
		direction = d;
	}
	
	// Moves Mothership in x-direction by given amount
	public void move(double dx) {
		mothership.move(dx, 0.0);
	}
	
	// Kills Mothership
	public void kill() {
		isAlive = false;
	}
	
	// Removes Mothership and bullets
	public void end() {
		mothership.removeFromCanvas();
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			bullet.kill();
		}
	}
	
	// Shoots new bullet
	public void shoot() {
		if (isAlive) {
			bullets.add(new Bullet(main, BULLET_VELOCITY, mothership.getX() + SHIP_WIDTH/2, mothership.getY() - BULLET_GAP, Color.WHITE, this, invaders, canvas));
		}
	}
	
	// Returns true if Mothership overlaps bullet given, false otherwise
	public boolean overlaps(FilledRect bullet) {
		return mothership.overlaps(bullet);
	}
	
	// Runs the Mothership ActiveObject 
	public void run() {
		while (isAlive) {
		    double step = mothership.getX() + direction;
		    if (step > 0 && step + mothership.getWidth() < canvas.getWidth()) {
		    	move(direction); 
		        pause(30);
		    } 
		}
		main.loseGame();
	}
}
