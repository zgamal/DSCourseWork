import java.awt.event.*;
public class DigitButtonListener implements ActionListener
{
	protected int val;					
	protected State state;	
	public DigitButtonListener(int newValue, State state) {
		val = newValue;
		this.state = state;
	}
	public void actionPerformed(ActionEvent evt) {
		this.state.addDigit(val);
	}
}