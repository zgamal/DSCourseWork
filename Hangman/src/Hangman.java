/*
 * EXTENTIONS DOCUMENTATION:
 * 
 * 1. Display wrong letters guessed by wletters array and lwletters label.
 * 
 * 2. Display time taken for game to end in seconds.
 * 
 * 3. Use the VisibleImage class to draw head of man rather than FramedOval.
 * 
 */

/*
 * THOUGHT QUESTIONS:
 * 
 * 1. onMouseEnter is invoked when the mouse enters the canvas. It's purpose is to keep track of when and where the mouse enters the WindowController's canvas. It has the parameter
 *    of the point where the mouse shows up. A program that might make sense to use them is, for example, a game where the side of which the user enters with his mouse matters.
 *    onMouseExit is sort of the opposite of onMouseEnter. It us invoked when the mouse exits the canvas. It's purpose is to keep track of when and where the mouse exits the 
 *    WindowConroller's canvas. It has the parameter of the point where the mouse leaves. A program where it might make sense to use this method is, for example, one that requires 
 *    the user to have the mouse in the canvas at all times, and so it would be useful to know if the user's mouse exits to notify him to get it back in. 
 *    
 * 2. objectDraw.rect is an abstract class that implements a resizable, 2d, drawable rectangle object. It's purpose is to draw a rectangle based on given inputs. The reason FramedOval extends it is that 
 * 	  it contains the most basic features and funcionality necessary to print a resizable, 2d, drawable rectangle object. Some of this functionality might be necssary for implements 
 *    FramedOval and thus the abstraction makes sense. 
 *    
 * 3. removeFromCanvas permenantly removes the object from the canvas if it is on it. In contrast, hide just "hides" the object on canvas and does not permanently remove it from it,
 * 	  but rather just hides it temporarily until show is invoked. Also, it is easier to reverse hide by just invoking show, while, on the other hand, to reverse removeFromCanvas you
 *    would have to construct/initialize the object again.
 * 
 */

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import objectdraw.*;

public class Hangman extends WindowController  implements KeyListener {

	// Instance variables
	protected String word = "";
	protected char[] letters;
	protected char[] wletters;; // stores incorrect letters
	protected int nwletters = 0; // stores number of wrong letters already guessed
	protected double starttime; // stores start time for game
	protected double endtime; // stores end time for game
	protected char[] puzzleLetters;
	protected boolean setup;
	protected int lettersRemaining;
	protected int playerNum = 0;
	
	// GUI elements
	protected Text label;
	protected Text lwletters; // displays wrong letters
	protected Text time; // displays time taken
	protected Text buttonText;
	protected FramedRect button;
	protected Text puzzle;
	protected Gallows gallows;
	protected Image head;
	
	// GUI constants
	protected static final int WINDOW_SIZE = 600;
	protected static final int TEXT_OFFSET = 10;
	protected static final int PUZZLE_OFFSET = 120;
	protected static final int BUTTON_WIDTH = 200;
	protected static final int BUTTON_HEIGHT = 40;
	
    public void begin() {
    	
        // Get ready to handle key focuses
        requestFocus();
        addKeyListener(this);
        canvas.addKeyListener(this);
            
        // Set up the GUI for Player to enter the target word.
        label = new Text("Player " + getPlayerNum() + ", please enter a word.", TEXT_OFFSET, TEXT_OFFSET, canvas);
        label.setFontSize(20);
       
        // label to display wrong guesses
        lwletters = new Text("", TEXT_OFFSET, TEXT_OFFSET + 25, canvas);
        lwletters.setFontSize(20);
        lwletters.hide();
        
        // label to display time taken 
        time = new Text("", TEXT_OFFSET, TEXT_OFFSET + 50, canvas);
        time.setFontSize(20);
        time.hide();
       
        setup = true;
            
        button = new FramedRect(WINDOW_SIZE/2 - BUTTON_WIDTH/2, WINDOW_SIZE/2 - BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT, canvas);
        button.setColor(Color.RED);
        button.hide();
            
        buttonText = new Text("Click when finished.", button.getX() + BUTTON_WIDTH/2, button.getY() + BUTTON_HEIGHT/2, canvas);
        buttonText.move(buttonText.getWidth()/-2.0, buttonText.getHeight()/-2.0);
        buttonText.hide();
            
        puzzle = new Text("Puzzle to Solve: ", WINDOW_SIZE/2, WINDOW_SIZE - PUZZLE_OFFSET, canvas);
        puzzle.setFontSize(30);
    	puzzle.moveTo(WINDOW_SIZE/2 - puzzle.getWidth()/2, puzzle.getY());	
    	
    	head = getImage("icons8-penguin-48.png");
    }
    
    // Required by KeyListener Interface.
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    
    public void keyTyped(KeyEvent e) {
    	if (setup) {
    		if (word.isEmpty()) puzzle.setText("Puzzle to Solve: ");
    		char letter = e.getKeyChar();
    		if (Character.isLetter(letter)) {
    			
    			/* Update the puzzle text with the letter
	    		 * that was just entered.
	    		 */
    			puzzle.setText(puzzle.getText() + "_ ");
    			word += letter;
	    		puzzle.moveTo(WINDOW_SIZE/2 - puzzle.getWidth()/2, puzzle.getY());
	    		if (word.length() == 1) {
	    			button.show();
	    			buttonText.show();
	    		}
    		} else if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE && ! word.isEmpty()) {
    			
    			/* Add logic to process the delete key having 
    			 * been pressed, adjusting the position of the puzzle
    			 * text accordingly.  Hide the "Click when finished" button 
    			 * if the word has been deleted entirely. 
    			 */
    			puzzle.setText(puzzle.getText().substring(0, puzzle.getText().length() - 2));
    			word = word.substring(0, word.length() - 1);
	    		puzzle.moveTo(WINDOW_SIZE/2 - puzzle.getWidth()/2, puzzle.getY());
	    		if (word.length() == 0) {
	    			buttonText.hide();
	    			button.hide();
	    		}
    		}
    	} else if (gallows.isAlive() ) { 
    		char guessedLetter = e.getKeyChar();
    		
    		/* Add logic to check if the letter
    		 * is in the word. Update the guess word
    		 * if the letter is found, otherwise hang
    		 * the man. Check if game is lost.
    		 */
    		if (word.indexOf(guessedLetter) >= 0) {
    			updateGuessWord(guessedLetter);
    		} else {
    	        gallows.hang();
    	        if (!gallows.isAlive()) {
    	    	    endtime = System.currentTimeMillis();
    	    	    time.setText("Time taken: " + ((endtime - starttime) / 1000) + " seconds");
    	    	    time.show();
    	            playerNum = (playerNum + 1) % 2;
    	            puzzle.setText("The word was: " + word);
    	    		puzzle.moveTo(WINDOW_SIZE/2 - puzzle.getWidth()/2, puzzle.getY());
    	            label.setText("Game Over! Player " + getPlayerNum() + " wins.");
    	            word = "";	
    	        }
    	        if (String.valueOf(wletters).indexOf(Character.toUpperCase(guessedLetter)) < 0) {
    	        	wletters[nwletters] = Character.toUpperCase(guessedLetter);
    	        	lwletters.setText("Incorrect guesses: " + new String(wletters));
    	        	lwletters.show();
    	        	nwletters++;
    	        }
    		}
    	}
    }
    
    public void onMousePress(Location point) {
    	if (button.contains(point) && !button.isHidden() && !word.isEmpty()) {
    		/* Add logic to exit setup mode and
    		 * start gameplay
    		 */
    	    starttime = System.currentTimeMillis();
    	    time.hide();
    		buttonText.hide();
    		button.hide();
    	    letters = word.toUpperCase().toCharArray();
    	    wletters = new char [6];
    	    lwletters.hide();
    	    puzzleLetters = new char[letters.length * 2];
    	    playerNum = (playerNum + 1) % 2;
    	    label.setText("Player " + getPlayerNum() + ", please type a key to guess a letter.");
    	    for (int i = 0; i < puzzleLetters.length; i += 2) {
    	    	puzzleLetters[i] = '_';
    	    	puzzleLetters[i+1] = ' ';
    	    }
    	    lettersRemaining = letters.length;
    	    setup = false;
    	    gallows = new Gallows(head, 150, 400, canvas);
    	}
    }
    
    public int getPlayerNum() {
    	return playerNum + 1;
    }
	
    public void updateGuessWord(char guessedLetter) {
    	
    	/* Add logic to update the guessed word.
    	 * Also include logic to test if the puzzle has
    	 * been solved (allowing the user to enter a new
    	 * word for their opponent if the puzzle is complete). 
    	 */	
    	for (int i = 0; i < letters.length; i++) {
    		if (letters[i] == Character.toUpperCase(guessedLetter)) {
    			if (puzzleLetters[i*2] != Character.toUpperCase(guessedLetter)) {
    				puzzleLetters[i*2] = Character.toUpperCase(guessedLetter);
    				lettersRemaining--;
    			}
    		}
    	}
    	puzzle.setText("Puzzle to Solve: " + new String(puzzleLetters));
    	if (lettersRemaining == 0) {
    	      endtime = System.currentTimeMillis();
    	      time.setText("Time taken: " + ((endtime - starttime) / 1000) + " seconds");
    	      time.show();
    	      label.setText("Congratulations! You solved the puzzle. Enter a new word.");
    	      word = "";
    	      gallows.clear();
    	      setup = true;
    	}
    }
    
    public static void main(String[] args) { 
        new Hangman().startController(WINDOW_SIZE, WINDOW_SIZE); 
	}
	
}
