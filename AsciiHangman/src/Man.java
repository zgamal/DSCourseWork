public class Man {

	protected static final int MAX_INCORRECT = 6; // constant to store max trials 
	protected int numIncorrect; // int to store incorrect trials

	// initializes man with zero incorrect trials
	public Man() {
		numIncorrect = 0;
	}
	
	// checks if man is alive
	public boolean isAlive() {
		return (numIncorrect < MAX_INCORRECT);
	}
	
	// increments number of incorrect trials by 1
	public void hang() {
		numIncorrect++;
	}
}