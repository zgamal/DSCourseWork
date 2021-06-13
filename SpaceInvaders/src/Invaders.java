import java.awt.Color;
import java.awt.Image;
import java.util.Random;
import java.util.Vector;

import objectdraw.*;

public class Invaders extends ActiveObject {
	
	// Constants for Invaders
	private static final int ALIEN_SIZE = 40;
	private static final int GAP_SIZE = 20;
	private static final int BULLET_VELOCITY = 20;
	private static final int BULLET_GAP = 5;
	
	// Instance variables for Invaders
	private VisibleImage[][] invaders = new VisibleImage[6][9];
	private SpaceInvaders main;
	private DrawingCanvas canvas;
	private Mothership ship;
	private Bullet bullet;
	private Vector<Bullet> bullets;
	private int count = 54;
	private Random r;
	
	// Constructs Invaders with parameters given
	public Invaders(Image img[][], SpaceInvaders main, DrawingCanvas canvas) {
		for (int r = 0; r < invaders.length; r++) {
			for (int c = 0; c < invaders[0].length; c++) {
				invaders[r][c] = new VisibleImage(img[r][c], c*GAP_SIZE + c*ALIEN_SIZE, r * (GAP_SIZE/2) + r * ALIEN_SIZE, ALIEN_SIZE, ALIEN_SIZE, canvas);
			}
		}
		this.main = main;
		this.canvas = canvas;
		bullets = new Vector<Bullet>();
		r = new Random();
		start();
	}
	
	// Returns array of Invader bullets  
	public Vector<Bullet> getBullets() {
		return bullets;
	}
	
	// Returns current score
	public int getScore() {
		return (54 - count) * 10;
	}

	// Returns the lowest invader row position in the y-direction
	public double getY() {
		double maxY = 0;
		for (int r = 0; r < invaders.length; r++) {
			for (int c = 0; c < invaders[0].length; c++) {
				if (invaders[r][c].getCanvas() != null) {
					double y = invaders[r][c].getY() + invaders[r][c].getHeight();
					if (y > maxY) {
						maxY = y; 
					}
				}
			}
		}
		return maxY;
	}
	
	// Returns true if given bullet overlaps any invader
	public boolean overlaps(FilledRect bullet) {
		for (int r = 0; r < invaders.length; r++) {
			for (int c = 0; c < invaders[0].length; c++) {
				if (bullet.overlaps(invaders[r][c])) {
					return true;
				}
			}
		}
		return false;
	}
	
	// Moves Invaders by given amount
	public void move(int dx, int dy) {
		for (int r = 0; r < invaders.length; r++) {
			for (int c = 0; c < invaders[0].length; c++) {
				invaders[r][c].move(dx, dy);
			}
		}
	}
	
	// Kills invader at given location
	public void kill(Location loc) {
		for (int r = 0; r < invaders.length; r++) {
			for (int c = 0; c < invaders[0].length; c++) {
				if (invaders[r][c].contains(loc)) {
					invaders[r][c].removeFromCanvas();
					count--;
				}
			}
		}
	}
	
	// Shoots bullet from random applicable invader
	public void shoot() {
		int col = this.r.nextInt(9);
		int row = 5;
		while (row >= 0 && this.invaders[row][col].getCanvas() == null) {
			row--; 
		}
		if (row < 0) {
		    return; 
		}
		VisibleImage shooter = invaders[row][col];
		bullets.add(new Bullet(main, BULLET_VELOCITY, shooter.getX() + shooter.getWidth()/2, shooter.getY() + shooter.getHeight() + BULLET_GAP, Color.YELLOW, main.ship, this, canvas));
	}
	 
	// Removes Invaders and bullets
	public void end() {
		for (int r = 0; r < invaders.length; r++) {
			for (int c = 0; c < invaders[0].length; c++) {
				if (invaders[r][c].getCanvas() != null) {
					invaders[r][c].removeFromCanvas();
				}
			}
		} 
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			bullet.kill();
		}
	}
	
	// Runs the Invaders ActiveObject
	public void run() {
	    int step = 0;
	    int level = 0;
	    int dx = 0;
	    int dy = 0;
	    while (count > 0) {
	    	pause(Math.max(1200 - level, 300));
	    	if (step < 4) {
	    		dx = 1;
	    		dy = 0;
	    	} else if (step == 4) {
	    		dx = 0;
	    		dy = 1;
	    	} else if (step == 9) {
	    		dx = 0;
	    		dy = 1;
	    		step = -1;
	    	} else {
	    		dx = -1;
	    		dy = 0;
	    	} 
	    	for (int r = 0; r < invaders.length; r++) {
	    		for (int c = 0; c < invaders[0].length; c++) {
	    			invaders[r][c].move((dx * (ALIEN_SIZE + GAP_SIZE)), (dy * (ALIEN_SIZE + GAP_SIZE/2)));
	    		}
	    	}
	    	step++;
	    	shoot();
	    	if (getY() >= canvas.getHeight()-75) {
	    		main.loseGame();
	        	level += 20;
	    	}
        } 
	    main.winGame();
	}
	
}
