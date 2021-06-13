import java.awt.Color;
import java.util.Vector;
import objectdraw.*;

public class Bullet extends ActiveObject {
	
	// Constants for Bullet
	private static final int BULLET_WIDTH = 4;
	private static final int BULLET_HEIGHT = 10;
	
	// Instance variables for Bullet
	private FilledRect bullet;
	private SpaceInvaders main;
	private DrawingCanvas canvas;
	private Mothership ship;
	private Invaders invaders;
	private double velocity;
	private boolean isAlive;
	
	// Constructs Bullet with parameters given
	public Bullet(SpaceInvaders main, double velocity, double posX, double posY, Color color, Mothership ship, Invaders invaders, DrawingCanvas canvas) {
		bullet = new FilledRect(posX, posY, BULLET_WIDTH, BULLET_HEIGHT, canvas);
		this.main = main;
		this.velocity = velocity;
		bullet.setColor(color);
		this.ship = ship;
		this.invaders = invaders;
		this.canvas = canvas;
		isAlive = true;
		start();
	}
	
	// Return location of Bullet in x-direction
	public double getX() {
		return bullet.getX();
	}
	
	// Return location of Bullet in y-direction
	public double getY() {
		return bullet.getY();
	}
	
	// Returns Bullet that overlaps this Bullet if it exits, null otherwise
	public Bullet overlapsBullets() {
		Vector<Bullet> bullets;
		bullets = invaders.getBullets();
		for (int i = 0; i < bullets.size(); i++) {
			if (bullet.overlaps(bullets.get(i).bullet) && !bullet.equals(bullets.get(i).bullet)) { 
				return bullets.get(i);
			}
		}
		return null;
	}
	
	// Move Bullet in y-direction by given amount
	public void move(int dy) {
		bullet.move(0, dy);
	}
	
	// Kills Bullet
	public void kill() {
		if (isAlive) {
			isAlive = false;
			bullet.removeFromCanvas();
		}
	}

	// Runs Bullet while conditions meet  
	public void run() {
		while (isAlive && this.getY() > 0 && this.getY() < canvas.getHeight() - BULLET_HEIGHT && !invaders.overlaps(bullet) && !ship.overlaps(bullet) && overlapsBullets() == null) {
			pause(Math.abs(100 / velocity));
		    bullet.move(0, (velocity / Math.abs(velocity)));
		} 
		if (ship.overlaps(bullet)) {
			ship.kill();
		} else if (invaders.overlaps(bullet)) {
			invaders.kill(bullet.getLocation());
			kill();
		} else if (overlapsBullets() != null) {
			overlapsBullets().kill();
			kill();
		} else {
			kill();
		}
	}
	
}
