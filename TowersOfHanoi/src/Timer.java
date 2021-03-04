import java.awt.*;
import objectdraw.*; 

public class Timer extends ActiveObject {
	
	// constant variables for Timer
	private static final int TIMERX = 50;
	private static final int TIMERY = 85;
	
	// variables for Timer
	private Text text;
	private int seconds; 
	private int running; 
	
	// Constructs Timer ActiveObject
	public Timer(DrawingCanvas canvas) {
		text = new Text("Seconds Elapsed: " + 0, TIMERX, TIMERY, canvas); 
		text.setFontSize(20);
		text.setColor(Color.ORANGE);
		running = 1;
		seconds = 0; 
		start(); 
	}	
	
	// Keeps track of time
	public void run() {
		while(true) {
			pause(1000); 
			seconds += running; 
			text.setText("Seconds Elapsed: " + seconds);
		}
	}
	
	// Toggles Timer off
	public void toggle() {
		if (running == 0) {
			running = 1;
		} else {
			running = 0;
		}
	}
	
	// Resets Timer
	public void reset() {
		seconds = 0;
		text.setText("Seconds Elapsed: " + seconds);
		running = 1;
	}

	public Text getTimer() {
		return text;
	}
}
