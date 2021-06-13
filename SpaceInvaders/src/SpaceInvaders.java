import objectdraw.*;
import java.awt.*;
import java.awt.event.*;

public class SpaceInvaders extends WindowController implements KeyListener {

	// Constants for the window
	private static final int HEIGHT = 800;
	private static final int WIDTH = 800;

	// Instance variables for the window
	Invaders invadersp;
	Image[][] invaderspack = new Image[6][9];
	Mothership ship;
	private Image shipImage;
	private int currentdirection;
	FilledRect background; 
	private Text t1;
	private Text t2;
	private boolean gameRunning;
	private boolean keyDown;
			
	// General set ups for the game
	public void begin() {
		
		/* This code will make it so the window cannot be resized */
		/*
		Container c = this;
		while(! (c instanceof Frame)) {
			c = c.getParent();
		}
		((Frame)c).setResizable(false);
		*/
		background = new FilledRect(0,0,WIDTH, HEIGHT, canvas);
		background.setColor(Color.WHITE);
		gameRunning = false;
		
		// Sets up main text
		t1 = new Text("Click to Start the Game.", canvas.getWidth()/ 2, canvas.getHeight()/2, canvas);
		t1.setFontSize(36);
		t1.setColor(Color.BLACK);
		t1.moveTo(canvas.getWidth()/2 - t1.getWidth()/2, canvas.getHeight()/2 - t1.getHeight()/2);
		
		// Sets up subtext
		t2 = new Text("Click to play again.", canvas.getWidth()/ 2, canvas.getHeight()/2, canvas);
		t2.setFontSize(26);
		t2.setColor(Color.RED);
		t2.moveTo(canvas.getWidth()/2 - t2.getWidth()/2, canvas.getHeight()/2 + 150 - t2.getHeight()/2);
		t2.hide();
		
		// Sets up the KeyListeners
		requestFocus();
		addKeyListener(this);
		canvas.addKeyListener(this);
		
		// Sets up the images for the Invaders
		Image invader1 = getImage("invader1.png");
		Image invader2 = getImage("invader2.png");
		Image invader3 = getImage("invader3.png");
		Image invader4 = getImage("invader4.png");
		Image invader5 = getImage("invader5.png");
		Image invader6 = getImage("invader6.png");
		Image[] invaders = {invader1, invader2, invader3, invader4, invader5, invader6};
		for (int r = 0; r < invaderspack.length; r++) {
			for (int c = 0; c < invaderspack[0].length; c++) {
				invaderspack[r][c] = invaders[r];
			}
		}
				
		// Sets up the image for the Mothership
		shipImage = getImage("ship.png");
	}
	
	// Starts game if not running when mouse clicked
	public void onMouseClick(Location l) {
		if (gameRunning == false) {
			startGame();
		}
		
	}
	
	// Handle the arrow keys by telling the ship to go in the direction of the arrow and shoot.
	public void keyTyped(KeyEvent e) {
		if ( e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP ) {
	       ship.shoot();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
		    ship.setDirection(-5);
		} else if ( e.getKeyCode() == KeyEvent.VK_RIGHT ) {
		    ship.setDirection(5);			
        }

	}

	// Remember that the key is no longer down.
	public void keyReleased(KeyEvent e) {
		keyDown = false;
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT ) {
			ship.setDirection(0);
		}
	}

	// Handle the pressing of the key the same as typing.
	public void keyPressed(KeyEvent e) {
		if (!keyDown) {
			keyTyped(e);
		}
		keyDown = true;
	}
	
	// Sets window for game prompt
	public void startGame() {
		gameRunning = true;
		invadersp = new Invaders(invaderspack, this, canvas);
		ship = new Mothership(shipImage, this, invadersp, canvas);
		t1.hide();
		t2.hide();
		background.setColor(Color.BLACK);

	}
	
	// Sets window when game is won
	public void winGame() {
		gameRunning = false;
		invadersp.end();
		ship.kill();
		t1.setText("Congratulations, you won! 540 points.");
		t1.setColor(Color.GREEN);
		t1.moveTo(canvas.getWidth()/2 - t1.getWidth()/2, canvas.getHeight()/2 - t1.getHeight()/2);
		t1.show();
		t2.setColor(Color.GREEN);
		t2.show();
	}
	
	// Sets window when game is lost
	public void loseGame() {
		gameRunning = false;
		invadersp.end();
		ship.end();
		t1.setText("Game over! Your score: " + invadersp.getScore() + "/540 points");
		t1.setColor(Color.RED);
		t1.moveTo(canvas.getWidth()/2 - t1.getWidth()/2, canvas.getHeight()/2 - t1.getHeight()/2);
		t1.show();
		t2.setColor(Color.RED);
		t2.show();
	}
	
	// Starts controller
    public static void main(String[] args) { 
        new SpaceInvaders().startController(WIDTH, HEIGHT); 
	}
}