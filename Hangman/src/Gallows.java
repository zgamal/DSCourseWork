import java.awt.Image;

import objectdraw.*;

public class Gallows {
	
	// GUI elements
	protected FilledRect base;
	protected FilledRect beam;
	protected FilledRect crossbeam;
	protected FilledRect rope;
	protected Man man;
	
	// GUI constants
	protected static final int BEAM_WIDTH = 10;
	protected static final int BASE_LENGTH = 120;
	protected static final int CROSSBEAM_LENGTH = 100;
	protected static final int TOP_YPOSITION = 300;

	// construct all gallows parts and hides them
	public Gallows(Image img, double xPos, double yPos, DrawingCanvas canvas) {
		man = new Man(img, xPos + BEAM_WIDTH * 16, yPos - BEAM_WIDTH * 25, canvas);
		base = new FilledRect(xPos, yPos, BASE_LENGTH, BEAM_WIDTH, canvas);
	    beam = new FilledRect(xPos + (BASE_LENGTH/2), yPos - TOP_YPOSITION, BEAM_WIDTH, TOP_YPOSITION, canvas);
	    crossbeam = new FilledRect(xPos + (BASE_LENGTH/2), yPos - TOP_YPOSITION, CROSSBEAM_LENGTH, BEAM_WIDTH, canvas);
	    rope = new FilledRect(xPos + (BASE_LENGTH/2 + CROSSBEAM_LENGTH), yPos - TOP_YPOSITION, BEAM_WIDTH, (CROSSBEAM_LENGTH/2), canvas);
	}
	
	// calls hang method in man
	public void hang() {
		man.hang();
	}
	
	// return status of man
	public boolean isAlive() {
		return man.isAlive();
	}
	
	// hides all gallows parts
	public void clear() {
	    base.hide();
	    beam.hide();
	    crossbeam.hide();
	    rope.hide();
	    man.clear();
	}
	
}
