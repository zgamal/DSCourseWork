import java.awt.Image;

import objectdraw.*;

public class Man {
	
	// GUI elements
	protected FramedOval head; 
	protected DrawableInterface[] bodyParts;
	
	// GUI constants
	protected static final int MAX_INCORRECT = 6;
	protected static final int HEAD_SIZE = 80;
	protected static final int BODY_SIZE = 80;
	protected static final int ARM_LENGTH = 50;
	protected static final double ABARM_LENGTH = 35.5;

	// Instance variables
	protected int numIncorrect;

	// constructs all body parts and hides them
	public Man(Image img, double xPos, double yPos, DrawingCanvas canvas) {
		bodyParts = new DrawableInterface[6];
		bodyParts[0] = new VisibleImage(img, xPos - (HEAD_SIZE/2), yPos, HEAD_SIZE, HEAD_SIZE, canvas);
		bodyParts[1] = new Line(xPos, yPos + HEAD_SIZE, xPos, (yPos + (HEAD_SIZE + BODY_SIZE)), canvas);
		bodyParts[2] = new Line(xPos, yPos + (HEAD_SIZE + BODY_SIZE/2), xPos - ABARM_LENGTH, yPos + (HEAD_SIZE + BODY_SIZE/2 - ABARM_LENGTH), canvas);
		bodyParts[3] = new Line(xPos, yPos + (HEAD_SIZE + BODY_SIZE/2), xPos + ABARM_LENGTH, yPos + (HEAD_SIZE + BODY_SIZE/2 - ABARM_LENGTH), canvas);
		bodyParts[4] = new Line(xPos, yPos + (HEAD_SIZE + BODY_SIZE), xPos - ABARM_LENGTH, yPos + (HEAD_SIZE + BODY_SIZE + ABARM_LENGTH), canvas);
		bodyParts[5] = new Line(xPos, yPos + (HEAD_SIZE + BODY_SIZE), xPos + ABARM_LENGTH, yPos + (HEAD_SIZE + BODY_SIZE + ABARM_LENGTH), canvas);
		clear();
	}
	
	// hides all body parts
	public void clear () {
		for (int i = 0; i < MAX_INCORRECT; i++) {
			bodyParts[i].hide();
		}
	}
	
	// show next body part and increments numIncorrect 
	public void hang() {
		bodyParts[numIncorrect].show();
		numIncorrect++;
	}
	
	// return status of man
	public boolean isAlive() {
		return (numIncorrect < MAX_INCORRECT);
	}
	
}
