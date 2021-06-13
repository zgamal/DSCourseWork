import structure.*;
import javax.swing.*;
public class State {
	protected int num;
	protected boolean progress;
	protected Stack storage;
	protected JLabel calcDisplay;
	public State(JLabel display) {
		calcDisplay = display;
		progress = false;
		storage = new StackList(); //a stack
	}
	public void addDigit(int value) {
		if (!progress) {
			num = value;
			progress = true;
		} else {
			num = 10 * num + value;
		} 
		calcDisplay.setText("" + num);
	}
	public void doOp(char op) {
		boolean error = false;
		if (progress)
			enter(); 
		if (storage.size() >= 2) {
			Integer second = (Integer) storage.pop();
			Integer first = (Integer)storage.pop();
			int result = 0;
			if (op=='+') {
				result = first + second;
			}else if (op=='-') {
				result = first - second;
			}else if (op=='*') {
				result = first * second;
			}else if (op=='/') {
				if (second != 0) {
					result = first / second;
				} 
			}else { 
				error = true;
			} 
			num = result;
			storage.push(result);
			calcDisplay.setText(Integer.toString(result));
		} else {
			error = true;
		} 
		if (error) {
			calcDisplay.setText("Error");
			storage.clear();
			progress = false;
		} 
	}
	public void enter() {
		storage.push(num);
		progress = false;
	}
	public void clear() {
		storage.clear();
		progress = false;
		calcDisplay.setText("0");
	}
	public void pop() {
		String display;
		if (!progress && !storage.isEmpty())
			storage.pop(); 
		if (storage.isEmpty()) {
			display = "0";
			num = 0;
		} else {
			display = storage.peek().toString();
			num = ((Integer)storage.peek());
		} 
		calcDisplay.setText(display);
		progress = false;
	}
}