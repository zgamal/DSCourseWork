/*
        Calculator.java - 1/97 Kim B. Bruce
        Revised 3/04
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
	Applet for a simple postfix calculator
 */
public class Calculator extends JFrame
{
	private static final int BORDER_SIZE = 10; // size of border around calculator
	protected static final int BUTTON_GAP = 5; // gap between successive buttons
	protected JLabel display;						// Display window for calculator
	protected JButton[] digitButton;				// Array of buttons representing 10 digits
	protected JButton clearButton, 				// Button to clear display
	enterButton, popButton,						// Buttons to enter #'s and pop off
	multButton, divButton, addButton, subButton;	// Arith. operation buttons
	protected State calcState;						// Object w/memory of computation in progress
	protected MiscButtonListener miscListener; 	// controls for misc. buttons
	/**
  	post: Set up calculator panel w/buttons and added it to applet
	 **/
	public Calculator()
	{
		super("Postfix Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		JPanel calcPanel = new JPanel();
		// lay out 6 rows w/1 column each
		calcPanel.setLayout(new GridLayout(6,1,BUTTON_GAP,BUTTON_GAP));	
		// create border around calcPanel
		calcPanel.setBorder(
				BorderFactory.createEmptyBorder(BORDER_SIZE,BORDER_SIZE,
						BORDER_SIZE,BORDER_SIZE));
		display = new JLabel("0", JLabel.RIGHT);		// display is right-justified
		display.setBorder(BorderFactory.createLoweredBevelBorder());
		calcPanel.add(display);				// display occupies first row
		calcState = new State(display);
		miscListener = new MiscButtonListener();			// Listener for misc buttons (non-digit, non-op)
		JPanel topPanel = new JPanel();    		// Panel for top two rows of calculator
		topPanel.setLayout(new GridLayout(1,2,BUTTON_GAP,BUTTON_GAP));		// lay out 1 row w/2 cols
		clearButton = new JButton("   Clear        ");
		clearButton.addActionListener(miscListener);    
		topPanel.add(clearButton);
		divButton = new JButton("/");
		divButton.addActionListener(new OpButtonListener("/", calcState));
		topPanel.add(divButton);
		calcPanel.add(topPanel);
		JPanel midPanel;
		digitButton = new JButton[BORDER_SIZE];		// Set up all buttons to enter digits
		for (int row = 0; row < 3; row++) {
			midPanel = new JPanel();
			midPanel.setLayout(new GridLayout(1,4,BUTTON_GAP,BUTTON_GAP));
			for (int col = 0; col < 3; col++) {
				int digit = (2-row)*3 + col + 1;
				digitButton[digit] = new JButton(""+digit);
				digitButton[digit].addActionListener(new DigitButtonListener(digit, calcState));
				midPanel.add(digitButton[digit]);
			}
			switch (row) {							// Put a different operator at end of each row
			case 0:   multButton = new JButton("*");
			multButton.addActionListener(new OpButtonListener("*", calcState));
			midPanel.add(multButton);
			break;
			case 1: addButton = new JButton("+");
			addButton.addActionListener(new OpButtonListener("+", calcState));
			midPanel.add(addButton);
			break;
			case 2: subButton = new JButton("-");
			subButton.addActionListener(new OpButtonListener("-", calcState));
			midPanel.add(subButton);
			break;
			}
			calcPanel.add(midPanel);
		}
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,3,BUTTON_GAP,BUTTON_GAP));
		popButton = new JButton("Pop");
		popButton.addActionListener(miscListener);
		bottomPanel.add(popButton);
		digitButton[0] = new JButton(""+0);
		digitButton[0].addActionListener(new DigitButtonListener(0, calcState));
		bottomPanel.add(digitButton[0]);
		enterButton = new JButton("Enter");
		enterButton.addActionListener(miscListener);
		bottomPanel.add(enterButton);
		calcPanel.add(bottomPanel);
		// Default layout for Frames is BorderLayout.
		contentPane.add(calcPanel, BorderLayout.CENTER);		// Adds calcPanel in center of Frame.
		setSize(210,300);
		contentPane.validate();
	}
	// Create and show Calculator so it can respond to events
	public static void main(String[] args){
		Calculator myCalc = new Calculator();
		myCalc.show();
	}
	// class which handles buttons labeled enter, clear, and pop.
	protected class MiscButtonListener implements ActionListener{
		/**
		 *	post:  Handle clicks on enter, clear, and pop buttons by calling the 
		 *		appropriate routines in the state.
		 *	Note that clicks on digit or operator buttons are handled by those buttons! 
		 */
		public void actionPerformed(ActionEvent evt)
		{
			if (evt.getSource() == enterButton) {				// Clicked on enter button
				calcState.enter();
			} else if (evt.getSource() == clearButton) {	// Clicked on clear button
				calcState.clear();
			} else if (evt.getSource() == popButton) {		// Clicked on pop button
				calcState.pop();
			}
		} 
	}
}