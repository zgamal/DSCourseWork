/*
 * THOUGHT QUESTIONS
 * 
 * 1. By using a graph of the two equation, an intersection point calculator, or some simple hand math, we can find that the intersection points 
 *    between the two equations are approximately n = 0.00100035, n = -0.000999654, and n = 29.7181. Program A ((2^n)/1000) takes less time than 
 *    Program B (1000*n^2) for values of n approximately between 0.00100035 and 29.7181. 
 *    
 * 2. Since String objects cannot be modified and have to be copied first, an algorith tom concatenate two n-length strings a and b can be done by 
 * 	  copying the first n-length string character by character into a new string and then copying n-length characters from the second string into 
 *    the new string. This operation will take 2n steps which is in the order of n, O(n). 
 * 
 * 3. public String toBinary(int i) {
 *        int x;
 *     	  if (i > 0) {
 *     	      x = i % 2;
 *     	      return (x + “” + ToBinary(i / 2));
 *     	  }
 *	      return “”;
 *    }
 *
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import objectdraw.*;

/*
 * A very simple drawing program.
 */

public class Scribbler extends WindowController implements ActionListener {
	
	// user modes for what operation is selected. We are using ints rather than boolean to allow for extension to other modes
	private static final int DRAWING = 1;
	private static final int MOVING = 2;
	private static final int COLORING = 3;
	
	// the current scribble
	private ScribbleInterface currentScribble;
	
	// the collection of scribbles
	private ScribbleCollectionInterface scribbles;
	
	// stores last point for drawing or dragging
	private Location lastPoint;
	
	// whether the most recent scribble has been selected for moving
	private boolean draggingScribble;
	
	// buttons that allow user to select modes
	private JButton setDraw, setMove, setErase, setColor;
	
	// Choice JButton to select color
	private JComboBox chooseColor;
	
	// new color for scribble
	private Color newColor;
	
	// label indicating current mode
	private JLabel modeLabel;
	
	// the current mode -- drawing mode by default
	private int chosenAction = DRAWING;
	
	// Create and hook up the user interface components.
	public void begin() {
		setDraw = new JButton("Draw");
		setMove = new JButton("Move");
		setColor = new JButton("Color");
		setErase = new JButton("Erase last");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(setDraw);
		buttonPanel.add(setMove);
		buttonPanel.add(setColor);
		
		chooseColor = new JComboBox();
		chooseColor.addItem("black");
		chooseColor.addItem("blue");
		chooseColor.addItem("green");
		chooseColor.addItem("yellow");
		chooseColor.addItem("red");

		
		
		JPanel choicePanel = new JPanel();
		choicePanel.add(setErase);
		choicePanel.add(chooseColor);
		
		JPanel controlPanel = new JPanel(new GridLayout(3,1));
		modeLabel = new JLabel("Current mode: drawing");
		controlPanel.add(modeLabel);
		controlPanel.add(buttonPanel);
		controlPanel.add(choicePanel);
		
		Container contentPane = this.getContentPane();
		contentPane.add(controlPanel, BorderLayout.SOUTH);
		
		// add listeners
		setDraw.addActionListener(this);
		setMove.addActionListener(this);
		setErase.addActionListener(this);
		setColor.addActionListener(this);
		
		// make the current scribble empty
		currentScribble = new EmptyScribble();
		scribbles =  new EmptyScribbleCollection();
		
		this.validate();
	}
	
	/*
	 * If in drawing mode then start with empty scribble.
	 * If in moving mode then prepare to move.
	 */
	public void onMousePress(Location point) {
		switch(chosenAction) {
			case DRAWING:
				// start with an empty scribble for drawing
				currentScribble = new EmptyScribble();	
				break;
				
			case MOVING:
				// check if user clicked on current scribble
				currentScribble = scribbles.scribbleSelected(point);
				draggingScribble = currentScribble.contains(point);
				break;
				
			case COLORING:
				currentScribble = scribbles.scribbleSelected(point);
				switch (chooseColor.getSelectedItem().toString()) {
					case "red":
						currentScribble.setColor(Color.RED);
						break;
						
					case "blue":
						currentScribble.setColor(Color.BLUE);
						break;
						
					case "green":
						currentScribble.setColor(Color.GREEN);
						break;
					
					case "yellow":	
						currentScribble.setColor(Color.YELLOW);
						break;
						
					case "black":
						currentScribble.setColor(Color.BLACK);
						break;
				}
				break;	
		}
		
		// remember point of press for drawing or moving
		lastPoint = point;
	}
	
	/*
	 * If in drawing mode, add a new segment to scribble. If in moving mode then move it.
	 */
	public void onMouseDrag(Location point) {
		if (chosenAction == DRAWING) {
			// add new line segment to current scribble
			Line newSegment = new Line(lastPoint, point, canvas);
			// change color of scribbled line to color selcted by user. Black is the default color.
			switch (chooseColor.getSelectedItem().toString()) {
				case "red":
					newSegment.setColor(Color.RED);
					break;
					
				case "blue":
					newSegment.setColor(Color.BLUE);
					break;
					
				case "green":
					newSegment.setColor(Color.GREEN);
					break;
				
				case "yellow":	
					newSegment.setColor(Color.YELLOW);
					break;
					
				case "black":
					newSegment.setColor(Color.BLACK);
					break;
			}
			currentScribble = new NonEmptyScribble(newSegment, currentScribble);
		} else if (chosenAction == MOVING) {
			if (draggingScribble) {
				// move current scribble
				currentScribble.move(point.getX() - lastPoint.getX(), point.getY() - lastPoint.getY());
			}
		}
		
		// update for next move or draw
		lastPoint = point;
	}
	
	/*
	 * Updates scribbles to approperiate collection.
	 */	
	public void onMouseRelease(Location point) {
		if (chosenAction == DRAWING) {
			scribbles =  new NonEmptyScribbleCollection(currentScribble, scribbles);
		}
	}
	
	/*
	 * Set mode according to JButton pressed.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == setDraw) {
			chosenAction = DRAWING;
			modeLabel.setText("Current mode: drawing.");
		} else if (e.getSource() == setMove) {
			chosenAction = MOVING;
			modeLabel.setText("Current mode: moving.");
		} else if (e.getSource()== setColor) {
			chosenAction = COLORING;
			modeLabel.setText("Current mode: coloring.");
		} else if (e.getSource()== setErase) {
			scribbles.getFirst().removeFromCanvas();
			scribbles = scribbles.getRest();
			currentScribble = scribbles.getFirst();
			modeLabel.setText("You erased something.");
		}
	}
	
	public static void main(String[] args) {
		new Scribbler().startController(600,600);
	}

}
