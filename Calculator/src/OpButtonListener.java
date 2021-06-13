import java.awt.event.*;
public class OpButtonListener implements ActionListener {
	protected State state;
	protected char code;
	public OpButtonListener(String op, State state) {
		this.state = state;
		code = op.charAt(0);
	}
	public void actionPerformed(ActionEvent evt) {
		this.state.doOp(code);
	}
}